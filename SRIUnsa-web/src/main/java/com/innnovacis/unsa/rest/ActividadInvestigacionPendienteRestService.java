/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIActividadGeneral;
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
@Path("/actividadInvestigacionPendiente")
@RequestScoped
public class ActividadInvestigacionPendienteRestService {
    
    @Inject
    private Logger log;
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/actividadesPaginacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesPendientes(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesPendientes(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesPendientes : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesPendientes Error : {0}", ex.getMessage());
        } 
        return builder.build();
    }
    
    @POST
    @Path("/aprobarActividad")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AprobarActividadInvestigacion(SRIActividadGeneral entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIActividadGeneral actividadGeneral= null;
        try {
            actividadGeneral = actividadInvestigacionBusiness.AprobarActividadInvestigacion(entidad);
            respuesta.put("body", actividadGeneral);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "Aprobar Actividad de Investigacion : {0}", actividadGeneral.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "Aprobar Actividad de Investigacion : {0}{1}", new Object[]{ex.getMessage(), entidad.toString()});
        }
        return builder.build();
    }
    
    @POST
    @Path("/descargarPdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarPdf(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesPendientes(entidad);
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFrom(respuesta);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento.pdf")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.pdf")
                .build();
    }
    
     @POST
    @Path("/descargarExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarExcel(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            
//            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = null;//generadorPDF.getArrayByteFrom(respuesta);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "Actividades Pendientes.pdf")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.pdf")
                .build();
    }
    
}
