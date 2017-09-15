/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IExoneracionBusiness;
import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIExoneracion;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIDocenteExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
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
@Path("/exoneracion")
@RequestScoped
public class ExoneracionRestService {

    @Inject
    private IExoneracionBusiness exoneracionBusiness;
    
    @Inject
    private IUsuarioBusiness usuarioBusiness;
    
    @Inject
    private Logger log;
    
    @POST
    @Path("/paginacionExoneracion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionExoneracion(SRIPaginacionObject object) {
        int total = exoneracionBusiness.GetTotalPaginacion(object);
        List<SRIExoneracion> lista = exoneracionBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarExoneracion/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIExoneracion body = new SRIExoneracion();
        
        try {
            body = exoneracionBusiness.GetById(id);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetExoneracionById - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetExoneracionById - Error : {0}{1}", new Object[]{ex.getMessage(), body.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        
        return builder.build();
    }
    
    @GET
    @Path("/listarExoneracion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIExoneracion> body = null;
        try {
            body = exoneracionBusiness.GetAll();
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetAllExoneracion - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetAllExoneracion - Error : {0}", new Object[]{ex.getMessage()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @POST
    @Path("/registrarExoneracion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveExoneracion(SRIExoneracion exoneracion){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        int body = -1;
        try {
            body = exoneracionBusiness.Insertar(exoneracion);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "SaveExoneracion - Success : {0}", exoneracion.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "SaveExoneracion - Error : {0}{1}", new Object[]{ex.getMessage(), exoneracion.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/actualizarExoneracion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ActualizarExoneracion(SRIExoneracion exoneracion){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = exoneracionBusiness.Update(exoneracion);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActualizarExoneracion - Success : {0}", exoneracion.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "ActualizarExoneracion - Error : {0}{1}", new Object[]{ex.getMessage(), exoneracion.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/eliminarExoneracion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EliminarExoneracion(SRIExoneracion exoneracion){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = exoneracionBusiness.Delete(exoneracion);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "EliminarExoneracion - Success : {0}", exoneracion.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "EliminarExoneracion - Error : {0}{1}", new Object[]{ex.getMessage(), exoneracion.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @GET
    @Path("/RegistrarUsuarioExoneracion/{idPersona:[0-9][0-9]*}/{idExoneracion:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RegistrarExoneracion(
            @PathParam("idPersona") int idPersona, 
            @PathParam("idExoneracion") int idExoneracion
    ) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIUsuario body = new SRIUsuario();
        
        try {
            body = exoneracionBusiness.RegistrarExoneracion(idPersona, idExoneracion);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "RegistrarExoneracion - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "RegistrarExoneracion - Error : {0}{1}", new Object[]{ex.getMessage(), body.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        
        return builder.build();
    }
    
    @POST
    @Path("/paginacionUsuarioExoneracion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuarioExoneracion(SRIPaginacionObject object) {
        int total = usuarioBusiness.GetTotalUsuarioExoneracion(object);
        List<SRIDocenteExoneracion> lista = usuarioBusiness.GetListaUsuarioExoneracion(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/EliminarUsuarioExoneracion/{idUsuario:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteUsuarioExoneracion(
            @PathParam("idUsuario") int idUsuario
    ) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIUsuario body = new SRIUsuario();
        
        try {
            body = exoneracionBusiness.DeleteUsuarioExoneracion(idUsuario);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "DeleteUsuarioExoneracion - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "DeleteUsuarioExoneracion - Error : {0}{1}", new Object[]{ex.getMessage(), body.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        
        return builder.build();
    }
}
