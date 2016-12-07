/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 *
 * @author innnovacis
 */
@Path("/usuarios")
@RequestScoped
public class UsuarioRestServices {

    @Inject
    private Logger log;

    @Inject
    private IUsuarioBusiness usuarioBusiness;
    
    @POST
    @Path("/paginacionUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuario(SRIPaginacionObject entidad) {
        
        int total = usuarioBusiness.GetTotalPaginacion(entidad);
        List<SRIUsuarioPersona> lista = usuarioBusiness.GetPagina(entidad);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIUsuario> getUsuarios() {
        
        return usuarioBusiness.GetAll();
    }
    
//    @POST
//    @Path("/registrarUsuarios")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response saveUsuario(Map<String, Object> entidad) {
//        System.out.println(entidad);
//        SRIUsuario usuario = (SRIUsuario) entidad.get("usuario");
//        SRIPersona persona = (SRIPersona) entidad.get("persona");
//        return null;
//    }
    
    @POST
    @Path("/registrarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUsuario(SRIUsuarioPersona usuarioPersona) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        int respuesta = -1;
        try{
            respuesta = usuarioBusiness.InsertarUsuarioPersona(usuarioPersona);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Registrar Usuario : {0}", usuarioPersona.toString());
        }catch(Exception ex){
            log.log(Level.INFO, "Registrar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuarioPersona.toString()});
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/updateUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateUsuario(SRIUsuario usuario) throws GeneralSecurityException, IOException {
        return usuarioBusiness.Update(usuario);
    }
    
    @PUT
    @Path("/deleteUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(SRIUsuario usuario) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        try{
            boolean respuesta = usuarioBusiness.Delete(usuario);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Eliminar Usuario : {0}", usuario.toString());
        } catch(Exception ex) {
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
            log.log(Level.INFO, "Eliminar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuario.toString()});
        }
        return builder.build();
    }
        
    @POST
    @Path("/autenticarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(SRIUsuario usuario) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        try {
            SRIUsuarioRolUtil respuesta = usuarioBusiness.AutenticarUsuario(usuario);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "Autenticar Usuario : {0}", usuario.toString());
        } catch(Exception ex) {
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
            log.log(Level.INFO, "Autenticar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuario.toString()});
        }
        return builder.build();
    }
    
}
