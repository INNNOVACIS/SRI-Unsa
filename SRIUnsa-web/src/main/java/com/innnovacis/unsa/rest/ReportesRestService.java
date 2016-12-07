/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/reportes")
@RequestScoped
public class ReportesRestService {
  
    @Inject
    private Logger log;
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/getActividadesDocentes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesByDocente(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesByDocente(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesByDocente - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesByDocente - Error : {0}", ex.getMessage());
        }
        
        return builder.build();
    }
    
    @POST
    @Path("/getActividadesDocentesDetalle")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesByDocenteDetalle(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesByDocenteDetalle(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesByDocenteDetalle - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesByDocenteDetalle - Error : {0}", ex.getMessage());
        }
        
        return builder.build();
    }
    
}
