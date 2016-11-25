/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IProcesoFlujoBusiness;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
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
@Path("/procesoflujo")
@RequestScoped
public class ProcesoFlujoRestService {

    @Inject
    private IProcesoFlujoBusiness procesoFlujoBusiness;
    
    @GET
    @Path("/listarProcesoFlujos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIProcesoFlujo> GetAll(){
        return procesoFlujoBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarProcesoFlujo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveProcesoFlujo(SRIProcesoFlujo entidad){
        return procesoFlujoBusiness.Insertar(entidad);
    }
    
    @PUT
    @Path("/actualizarProcesoFlujo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ActualizarProcesoFlujo(SRIProcesoFlujo entidad){
        return procesoFlujoBusiness.Update(entidad);
    }
    
    @PUT
    @Path("/eliminarProcesoFlujo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean EliminarProcesoFlujo(SRIProcesoFlujo entidad){
        return procesoFlujoBusiness.Delete(entidad);
    }
}
