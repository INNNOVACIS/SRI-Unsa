/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoNivelBusiness;
import com.innnovacis.unsa.model.SRITipoNivel;
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
@Path("/tipoNivel")
@RequestScoped
public class TipoNivelRestService {

    @Inject
    private ITipoNivelBusiness tipoNivelBusiness;
    
    @GET
    @Path("/listarTipoNivel")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRITipoNivel> GetAll(){
        return tipoNivelBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarTipoNivel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveTipoNivel(SRITipoNivel tipoNivel){
        return tipoNivelBusiness.Insertar(tipoNivel);
    }
    
    @PUT
    @Path("/updateTipoNivel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateTipoNivel(SRITipoNivel tipoNivel){
        return tipoNivelBusiness.Update(tipoNivel);
    }
    
    @PUT
    @Path("/deleteTipoNivel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteTipoNivel(SRITipoNivel tipoNivel){
        return tipoNivelBusiness.Delete(tipoNivel);
    }
}
