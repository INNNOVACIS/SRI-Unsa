/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacion")
@RequestScoped
public class ActividadInvestigacionRestService {

    @Inject
    private IActividadInvestigacionBusiness investigacionBusiness;
    
    @GET
    @Path("/listarActividadInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIActividadInvestigacion> GetAll(){
        return investigacionBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarActividadInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveActividadInvestigacion(SRIActividadInvestigacion investigacion){
        return investigacionBusiness.Insertar(investigacion);
    }
    
    @PUT
    @Path("/updateActividadInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateActividadInvestigacion(SRIActividadInvestigacion investigacion){
        return investigacionBusiness.Update(investigacion);
    }
    
    @PUT
    @Path("/deleteActividadInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteActividadInvestigacion(SRIActividadInvestigacion investigacion){
        return investigacionBusiness.Delete(investigacion);
    }
    
    @GET
    @Path("getActividad/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public SRIActividadInvestigacion getActividadById(@PathParam("id") int id) {
        SRIActividadInvestigacion actividadInvestigacion = investigacionBusiness.Get(id);
        if (actividadInvestigacion == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return actividadInvestigacion;
    }
}
