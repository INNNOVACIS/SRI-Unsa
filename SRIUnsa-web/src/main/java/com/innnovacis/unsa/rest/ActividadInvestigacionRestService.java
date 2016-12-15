/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacion")
@RequestScoped
public class ActividadInvestigacionRestService {

    @Inject
    private Logger log;
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @GET
    @Path("/listarActividadInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIActividadInvestigacion>  lstActividadInvestigacion = null;
        try {
            lstActividadInvestigacion = actividadInvestigacionBusiness.GetAll();
            respuesta.put("body", lstActividadInvestigacion);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActividadInvestigacion - GetAll : {0}", lstActividadInvestigacion.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "ActividadInvestigacion - GetAll - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @PUT
    @Path("/deleteActividadInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteActividadInvestigacion(SRIActividadInvestigacion investigacion){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = actividadInvestigacionBusiness.Delete(investigacion);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActividadInvestigacionDelete - Success : {0}", investigacion.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "ActividadInvestigacionDelete - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @GET
    @Path("getActividad/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActividadInvestigacionById(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIActividadGeneral body = null;
        try {
            body = actividadInvestigacionBusiness.Get(id);
            respuesta.put("body", body);
            if (body == null) {
                builder = Response.status(Response.Status.NOT_FOUND).entity(respuesta);
                log.log(Level.INFO, "GetActividadById - Nulo : {0}");
            } else {
                builder = Response.status(Response.Status.OK).entity(respuesta);
                log.log(Level.INFO, "GetActividadById - Success : {0}", body.toString());
            }
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "ActividadInvestigacionDelete - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
        
    @PUT
    @Path("/updateActividadInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateActividadInvestigacion(SRIActividadGeneral entidad){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = actividadInvestigacionBusiness.Update(entidad);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActividadInvestigacionUpdate - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "ActividadInvestigacionUpdate - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/registrarActividad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RegistrarActividad(SRIActividadGeneral entidad){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIActividadGeneral body = null;
        try {
            body = actividadInvestigacionBusiness.RegistrarActividad(entidad);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "RegistrarActividad - Success : {0}", body.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "RegistrarActividad - Error : {0}", ex.getMessage());
        }
        return builder.build();        
    }
    
    @POST
    @Path("/enviarEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EnviarEmail(SRIActividadGeneral entidad){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = actividadInvestigacionBusiness.EnviarEmail(entidad);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "EnviarEmail - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "EnviarEmail - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
}
