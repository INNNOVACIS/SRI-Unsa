/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.GenerateExcel;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocentesActividades;
import com.innnovacis.unsa.util.SRIDocentesActivosInactivosFacultad;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRITotalTipoActividad;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioHome;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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
import javax.ws.rs.PathParam;

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
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/google")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AuthGoogle(String credentials) {
        
        String tmp = credentials;
        System.out.println("------------------------------------>" + tmp);
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", 0);
        responseObj.put("lista", "");
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
}
