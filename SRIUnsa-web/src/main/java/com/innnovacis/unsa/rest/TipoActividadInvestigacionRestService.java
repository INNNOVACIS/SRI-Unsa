/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoActividadInvestigacionBusiness;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import com.innnovacis.unsa.util.GenerateExcel;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.ArrayList;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/tipoInvestigacion")
@RequestScoped
public class TipoActividadInvestigacionRestService {

    @Inject
    private ITipoActividadInvestigacionBusiness tipoInvestigacionBusiness;
    
    @POST
    @Path("/paginacionTipoInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionTipoInvestigacion(SRIPaginacionObject object) {
        int total = tipoInvestigacionBusiness.GetTotalPaginacion(object);
        List<SRITipoActividadInvestigacion> lista = tipoInvestigacionBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarTipoInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRITipoActividadInvestigacion> GetAll(){
        return tipoInvestigacionBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarTipoInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveTipoInvestigacion(SRITipoActividadInvestigacion tipoInvestigacion){
        return tipoInvestigacionBusiness.Insertar(tipoInvestigacion);
    }
    
    @PUT
    @Path("/updateTipoInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateTipoInvestigacion(SRITipoActividadInvestigacion tipoInvestigacion){
        return tipoInvestigacionBusiness.Update(tipoInvestigacion);
    }
    
    @PUT
    @Path("/deleteTipoInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteTipoInvestigacion(SRITipoActividadInvestigacion tipoInvestigacion){
        return tipoInvestigacionBusiness.Delete(tipoInvestigacion);
    }

}
