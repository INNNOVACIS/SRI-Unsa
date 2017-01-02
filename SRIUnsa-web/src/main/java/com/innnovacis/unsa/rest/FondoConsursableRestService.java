/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IFondoConcursableBusiness;
import com.innnovacis.unsa.model.SRIFondoConcursable;
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
    @Path("/paginacionFondoConcursable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionFondo(SRIPaginacionObject object) {
        
        int total = fondoBusiness.GetTotalPaginacion(object);
        List<SRIFondoConcursable> lista = fondoBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
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
