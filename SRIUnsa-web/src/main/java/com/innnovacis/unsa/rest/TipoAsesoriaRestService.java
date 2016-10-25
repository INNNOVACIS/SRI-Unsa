/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoAsesoriaBusiness;
import com.innnovacis.unsa.model.SRITipoAsesoria;
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
@Path("/tipoAsesoria")
@RequestScoped
public class TipoAsesoriaRestService {

    @Inject
    private ITipoAsesoriaBusiness tipoAsesoriaBusiness;
    
    @GET
    @Path("/listarTipoAsesorias")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRITipoAsesoria> GetAll(){
        return tipoAsesoriaBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarTipoAsesorias")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveTipoAsesoria(SRITipoAsesoria tipoAsesoria){
        return tipoAsesoriaBusiness.Insertar(tipoAsesoria);
    }
    
    @PUT
    @Path("/updateTipoAsesorias")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateTipoAsesoria(SRITipoAsesoria tipoAsesoria){
        return tipoAsesoriaBusiness.Update(tipoAsesoria);
    }
    
    @PUT
    @Path("/deleteTipoAsesorias")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteTipoAsesoria(SRITipoAsesoria tipoAsesoria){
        return tipoAsesoriaBusiness.Delete(tipoAsesoria);
    }
}
