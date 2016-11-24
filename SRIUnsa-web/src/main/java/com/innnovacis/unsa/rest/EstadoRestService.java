/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IEstadoBusiness;
import com.innnovacis.unsa.model.SRIEstado;
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
@Path("/estado")
@RequestScoped
public class EstadoRestService {

    @Inject
    private IEstadoBusiness estadoBusiness;
    
    @GET
    @Path("/listarEstados")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIEstado> GetAll(){
        return estadoBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveEstado(SRIEstado estado){
        return estadoBusiness.Insertar(estado);
    }
    
    @PUT
    @Path("/actualizarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ActualizarEstado(SRIEstado estado){
        return estadoBusiness.Update(estado);
    }
    
    @PUT
    @Path("/deleteRoles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean EliminarEstado(SRIEstado estado){
        return estadoBusiness.Delete(estado);
    }
}
