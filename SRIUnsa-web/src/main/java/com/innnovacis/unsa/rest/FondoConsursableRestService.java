/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IFondoConcursableBusiness;
import com.innnovacis.unsa.business.ISemestreBusiness;
import com.innnovacis.unsa.model.SRIFondoConcursable;
import com.innnovacis.unsa.model.SRISemestre;
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
@Path("/fondo")
@RequestScoped
public class FondoConsursableRestService {

    @Inject
    private IFondoConcursableBusiness fondoBusiness;
    
    @GET
    @Path("/listarFondos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIFondoConcursable> GetAll(){
        return fondoBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarFondos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveFondo(SRIFondoConcursable fondo){
        return fondoBusiness.Insertar(fondo);
    }
    
    @PUT
    @Path("/updateFondos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateFondo(SRIFondoConcursable fondo){
        return fondoBusiness.Update(fondo);
    }
    
    @PUT
    @Path("/deleteFondos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteFondo(SRIFondoConcursable fondo){
        return fondoBusiness.Delete(fondo);
    }
}
