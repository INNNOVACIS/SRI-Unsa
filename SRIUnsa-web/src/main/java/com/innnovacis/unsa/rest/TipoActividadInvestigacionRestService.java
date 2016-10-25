/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoActividadInvestigacionBusiness;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author innnovacis
 */
@Path("/tipoInvestigacion")
@RequestScoped
public class TipoActividadInvestigacionRestService {

    @Inject
    private ITipoActividadInvestigacionBusiness tipoInvestigacionBusiness;
    
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
