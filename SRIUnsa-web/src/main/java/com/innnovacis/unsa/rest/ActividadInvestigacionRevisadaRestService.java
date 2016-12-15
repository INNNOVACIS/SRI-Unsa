/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import com.innnovacis.unsa.util.SRIPaginacion;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacionRevisada")
@RequestScoped
public class ActividadInvestigacionRevisadaRestService {

    @Inject
    private Logger log;
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/actividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesRevisadas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesRevisadas(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesRevisadas : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesRevisadas - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/getActividadesRevisadasMasivas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesRevisadasMasivas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesRevisadasMasivas(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "getActividadesRevisadasMasivas : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "getActividadesRevisadasMasivas - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/aprobarActividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AprobarActividadesInvestigacion(List<SRIActividadGeneral> entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIActividadGeneral> actividadesGenerales= null;
        try {
            actividadesGenerales = actividadInvestigacionBusiness.AprobarActividadInvestigacionMasivo(entidad);
            respuesta.put("body", actividadesGenerales);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "Aprobar Actividad de Investigacion : {0}", actividadesGenerales.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "Aprobar Actividad de Investigacion : {0}{1}", new Object[]{ex.getMessage(), entidad.toString()});
        }
        return builder.build();
    }
    
    @GET
    @Path("getCabeceraMasiva/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetCabeceraMasiva(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRICabeceraDetalleMasiva> body = null;
        try {
            body = actividadInvestigacionBusiness.GetCabeceraMasiva(id);
            respuesta.put("body", body);
            if (body == null) {
                builder = Response.status(Response.Status.NOT_FOUND).entity(respuesta);
                log.log(Level.INFO, "GetCabeceraMasiva - Nulo : {0}");
            } else {
                builder = Response.status(Response.Status.OK).entity(respuesta);
                log.log(Level.INFO, "GetCabeceraMasiva - Success : {0}", body.toString());
            }
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetCabeceraMasiva - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/getDetalleMasiva")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetDetalleMasiva(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetDetalleMasiva(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetDetalleMasiva : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetDetalleMasiva - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
}
