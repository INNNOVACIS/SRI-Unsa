/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IFlujoActorBusiness;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Path("/flujoactor")
@RequestScoped
public class FlujoActorRestServices {

    @Inject
    private IFlujoActorBusiness flujoActorBusiness;
    
    @POST
    @Path("/paginacionActores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuario(SRIPaginacionObject object) {
        
        int total = flujoActorBusiness.GetTotalPaginacion(object);
        List<SRIFlujoActor> lista = flujoActorBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarActores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIFlujoActor> getActores() {
        return flujoActorBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarActores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int saveActor(SRIFlujoActor object) {        
        return flujoActorBusiness.Insertar(object);
    }
    
    @PUT
    @Path("/updateActores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateActor(SRIFlujoActor object) {
        
        return flujoActorBusiness.Update(object);
    }
    
    @PUT
    @Path("/deleteActores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteActor(SRIFlujoActor object) {
        return flujoActorBusiness.Delete(object);
    }
    
}