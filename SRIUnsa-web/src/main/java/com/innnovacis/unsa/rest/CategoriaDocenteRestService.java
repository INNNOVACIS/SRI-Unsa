/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ICategoriaDocenteBusiness;
import com.innnovacis.unsa.model.SRICategoriaDocente;
import com.innnovacis.unsa.util.SRIPaginacionObject;
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
@Path("/categoriaDocente")
@RequestScoped
public class CategoriaDocenteRestService {

    @Inject
    private ICategoriaDocenteBusiness categoriaDocenteBusiness;
    
    @Inject
    private Logger log;
    
    @POST
    @Path("/paginacionCategoriaDocente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionCategoriaDocente(SRIPaginacionObject object) {
        int total = categoriaDocenteBusiness.GetTotalPaginacion(object);
        List<SRICategoriaDocente> lista = categoriaDocenteBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarCategoriaDocente/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetById(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRICategoriaDocente body = new SRICategoriaDocente();
        
        try {
            body = categoriaDocenteBusiness.GetById(id);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetCategoriaDocenteById - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetCategoriaDocenteById - Error : {0}{1}", new Object[]{ex.getMessage(), body.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        
        return builder.build();
    }
    
    @GET
    @Path("/listarCategoriaDocentes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRICategoriaDocente> body = null;
        try {
            body = categoriaDocenteBusiness.GetAll();
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetAllCategoriaDocente - Success : {0}", body.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "GetAllCategoriaDocente - Error : {0}", new Object[]{ex.getMessage()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @POST
    @Path("/registrarCategoriaDocente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveCategoriaDocente(SRICategoriaDocente categoriaDocente){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        int body = -1;
        try {
            body = categoriaDocenteBusiness.Insertar(categoriaDocente);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "SaveCategoriaDocente - Success : {0}", categoriaDocente.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "SaveCategoriaDocente - Error : {0}{1}", new Object[]{ex.getMessage(), categoriaDocente.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/actualizarCategoriaDocente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ActualizarCategoriaDocente(SRICategoriaDocente categoriaDocente){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = categoriaDocenteBusiness.Update(categoriaDocente);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "ActualizarCategoriaDocente - Success : {0}", categoriaDocente.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "ActualizarCategoriaDocente - Error : {0}{1}", new Object[]{ex.getMessage(), categoriaDocente.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/eliminarCategoriaDocente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EliminarCategoriaDocente(SRICategoriaDocente categoriaDocente){
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        boolean body = false;
        try {
            body = categoriaDocenteBusiness.Delete(categoriaDocente);
            respuesta.put("body", body);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "EliminarCategoriaDocente - Success : {0}", categoriaDocente.toString());
        } catch(Exception ex) {
            log.log(Level.INFO, "EliminarCategoriaDocente - Error : {0}{1}", new Object[]{ex.getMessage(), categoriaDocente.toString()});
            respuesta.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }
        return builder.build();
    }
}
