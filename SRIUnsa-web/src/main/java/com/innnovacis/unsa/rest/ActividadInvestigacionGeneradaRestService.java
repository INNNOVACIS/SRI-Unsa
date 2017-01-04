/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
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
@Path("/actividadInvestigacionGenerada")
@RequestScoped
public class ActividadInvestigacionGeneradaRestService {
  
    @Inject
    private Logger log;
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/actividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesGeneradas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesGeneradas - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesGeneradas - Error : {0}", ex.getMessage());
        }
        
        return builder.build();
    }
    
    @POST
    @Path("/descargarPdf")
    public void descargarPdf(SRIPaginacion entidad)  {
        GeneratePdf pdfEjemplo = new GeneratePdf();        
        try {
            pdfEjemplo.generarPdfEjemplo();
            log.log(Level.INFO, "Se genera PDF");
        } catch(FileNotFoundException ex) {
            log.log(Level.INFO, "Error File Not Found : {0}", ex.getMessage());
        }catch(DocumentException ex2) {
            log.log(Level.INFO, "Error generacion documento : {0}", ex2.getMessage());
        }
    }
}
