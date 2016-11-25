/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IFlujoAristaBusiness;
import com.innnovacis.unsa.model.SRIFlujoArista;
import com.innnovacis.unsa.util.SRIFlujoAristaActorUtil;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/flujoarista")
@RequestScoped
public class FlujoAristaRestService {

    @Inject
    private IFlujoAristaBusiness flujoAristaBusiness;
    
    @POST
    @Path("/paginacionFlujoArista")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionFlujoArista(SRIPaginacionObject object) {
        
        int total = flujoAristaBusiness.GetTotalPaginacion(object);
        List<SRIFlujoAristaActorUtil> lista = flujoAristaBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarFlujoArista/{idOrigen:[0-9][0-9]*}/{idEstado:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public SRIFlujoArista GetFlujoAristaByIdOrigenIdEstado(@PathParam("idOrigen") int idOrigen, @PathParam("idEstado") int idEstado) {
        return flujoAristaBusiness.GetFlujoAristaByIdOrigenIdEstado(idOrigen, idEstado);        
    }
    
    @GET
    @Path("/listarFlujoAristas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIFlujoArista> GetAll(){
        return flujoAristaBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarFlujoArista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveFlujoArista(SRIFlujoArista entidad){
        return flujoAristaBusiness.Insertar(entidad);
    }
    
    @PUT
    @Path("/actualizarFlujoArista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ActualizarFlujoArista(SRIFlujoArista entidad){
        return flujoAristaBusiness.Update(entidad);
    }
    
    @PUT
    @Path("/eliminarFlujoArista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean EliminarFlujoArista(SRIFlujoArista entidad){
        return flujoAristaBusiness.Delete(entidad);
    }
}
