/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IEstadoBusiness;
import com.innnovacis.unsa.model.SRIEstado;
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
@Path("/estado")
@RequestScoped
public class EstadoRestService {

    @Inject
    private IEstadoBusiness estadoBusiness;
    
    @Inject
    private Logger log;
    
    @GET
    @Path("/listarEstado/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIEstado body = new SRIEstado();
        
        try {
            body = estadoBusiness.GetById(id);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetEstadoById - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetEstadoById - Error : {0}{1}", new Object[]{ex.getMessage(), body.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        
        return builder.build();
    }
    
    @GET
    @Path("/listarEstados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIEstado> body = null;
        try {
            body = estadoBusiness.GetAll();
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetAllEstado - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetAllEstado - Error : {0}", new Object[]{ex.getMessage()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @POST
    @Path("/registrarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveEstado(SRIEstado estado){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        int body = -1;
        try {
            body = estadoBusiness.Insertar(estado);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "SaveEstado - Success : {0}", estado.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "SaveEstado - Error : {0}{1}", new Object[]{ex.getMessage(), estado.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/actualizarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ActualizarEstado(SRIEstado estado){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = estadoBusiness.Update(estado);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActualizarEstado - Success : {0}", estado.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "ActualizarEstado - Error : {0}{1}", new Object[]{ex.getMessage(), estado.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/eliminarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EliminarEstado(SRIEstado estado){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = estadoBusiness.Delete(estado);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "EliminarEstado - Success : {0}", estado.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "EliminarEstado - Error : {0}{1}", new Object[]{ex.getMessage(), estado.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
}
