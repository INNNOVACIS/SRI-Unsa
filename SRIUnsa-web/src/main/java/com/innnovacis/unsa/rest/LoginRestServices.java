/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.util.AuthUtils;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.client.ClientConfig;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author innnovacis
 */
@Path("/auth")
@RequestScoped
public class LoginRestServices {

    @Inject
    private Logger log;

    @Inject
    private IUsuarioBusiness usuarioBusiness;
    
//    @Default
//    private Client client;
    
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CODE_KEY = "code", GRANT_TYPE_KEY = "grant_type";
    public static final String AUTH_CODE = "authorization_code";
    
    public static final ObjectMapper MAPPER = new ObjectMapper();
   
    private WebTarget target;
    private ClientConfig config;
    private Client client;
    
//    public LoginRestServices(final Client client) {
//        this.client = client;
//    }
    
    @POST
    @Path("/google")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AuthGoogle(@Valid final Payload payload,
      @Context final HttpServletRequest request) throws JsonMappingException, IOException {
        
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        
        final String accessTokenUrl = "https://accounts.google.com/o/oauth2/token";
        final String peopleApiUrl = "https://www.googleapis.com/plus/v1/people/me/openIdConnect";
        Response response_google;
        
//        config = new ClientConfig();
        client = ClientBuilder.newClient();
        
        
        // Step 1. Exchange authorization code for access token.
        final MultivaluedMap<String, String> accessData = new MultivaluedHashMap<String, String>();
        accessData.add(CLIENT_ID_KEY, payload.getClientId());
        accessData.add(REDIRECT_URI_KEY, payload.getRedirectUri());
        accessData.add(CLIENT_SECRET, "qjw_MsdT-ZZOoZm0dN_1cbKV");
        accessData.add(CODE_KEY, payload.getCode());
        accessData.add(GRANT_TYPE_KEY, AUTH_CODE);
        response_google = client.target(accessTokenUrl).request().post(Entity.form(accessData));
        accessData.clear();
        
        // Step 2. Retrieve profile information about the current user.
        final String accessToken = (String) getResponseEntity(response_google).get("access_token");
        response_google = client.target(peopleApiUrl).request("text/plain")
                        .header(AuthUtils.AUTH_HEADER_KEY, String.format("Bearer %s", accessToken)).get();
        
        final Map<String, Object> userInfo = getResponseEntity(response_google);
        String userEmail = userInfo.get("email").toString();
        
        try {
            
            SRIUsuarioLogin respuesta = usuarioBusiness.LoginGoogle(userEmail);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "Autenticar Usuario : {0}", userEmail);
        } catch(Exception ex) {
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
            log.log(Level.INFO, "Autenticar Usuario : {0}{1}", new Object[]{ex.getMessage(),userEmail});
        }
        return builder.build();
        
        
    }
    
    /*
    * Inner classes for entity wrappers
    */
    public static class Payload {
        @NotBlank
        String clientId;

        @NotBlank
        String redirectUri;

        @NotBlank
        String code;

        public String getClientId() {
            return clientId;
        }

        public String getRedirectUri() {
            return redirectUri;
        }

        public String getCode() {
            return code;
        }
    }
    
    /*
    * Helper methods
    */
    private Map<String, Object> getResponseEntity(final Response response) throws JsonParseException,
        JsonMappingException, IOException {
        return MAPPER.readValue(response.readEntity(String.class),
                                new TypeReference<Map<String, Object>>() {});
    }

    
}
