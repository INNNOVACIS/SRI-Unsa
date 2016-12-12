/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IPlantillaDocumentoBusiness;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.model.SRIPlantillaDocumento;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/plantillaDocumento")
@RequestScoped
public class PlantillaDocumentoRestService {

    @Inject
    private Logger log;
    
    @Inject
    private IPlantillaDocumentoBusiness plantillaDocumentoBusiness;

    
    @POST
    @Path("/GetPlantillaDocumentoByFacultad")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetPlantillaDocumentoByFacultad(SRIEstructuraOrganizacion entidad) {
        
        List<SRIPlantillaDocumento> lista = plantillaDocumentoBusiness.GetPlantillaDocumentoByFacultad(entidad);
        Map<String, Object> responseObj = new HashMap<>();        
        responseObj.put("body", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @POST
    @Path("/GetPlantillaDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetPlantillaDocumento(SRIPaginacionObject entidad) {
        
        int total = plantillaDocumentoBusiness.GetTotalPaginacion(entidad);
        List<SRIPlantillaDocumento> lista = plantillaDocumentoBusiness.GetPagina(entidad);
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @POST
    @Path("/registrarCampo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SavePlantilla(SRIPlantillaDocumento plantilla){
        int respuesta = -1;
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();        
        try{
            respuesta = plantillaDocumentoBusiness.Insertar(plantilla);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Registrar Plantilla : {0}", plantilla.toString());
        }catch(Exception ex){
            log.log(Level.INFO, "Registrar Plantilla : {0}{1}", new Object[]{ex.getMessage(), plantilla.toString()});
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/actualizarCampo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdatePlantilla(SRIPlantillaDocumento plantilla){
        return plantillaDocumentoBusiness.Update(plantilla);
    }
    
    @PUT
    @Path("/eliminarCampo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeletePlantilla(SRIPlantillaDocumento plantilla){
        return plantillaDocumentoBusiness.Delete(plantilla);
    }
}
