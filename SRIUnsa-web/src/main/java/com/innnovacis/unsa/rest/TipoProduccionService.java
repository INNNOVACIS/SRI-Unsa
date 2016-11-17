/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoProduccionBusiness;
import com.innnovacis.unsa.model.SRITipoProduccion;
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
@Path("/tipoProduccion")
@RequestScoped
public class TipoProduccionService {

    @Inject
    private ITipoProduccionBusiness tipoProduccionBusiness;
    
    @POST
    @Path("/paginacionTipoProduccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionTipoNivel(SRIPaginacionObject object) {
        int total = tipoProduccionBusiness.GetTotalPaginacion(object);
        List<SRITipoProduccion> lista = tipoProduccionBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarTipoProduccion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRITipoProduccion> getListTipoProduccion() {
        return tipoProduccionBusiness.GetAll();
    }
    
    
    @GET
    @Path("/getByIdTipoProduccion/{IdTipoProduccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public SRITipoProduccion getByIdTipoProduccion(@PathParam("IdTipoProduccion")long IdTipoProduccion) {
        
        return tipoProduccionBusiness.Get((int)IdTipoProduccion);
    }
    
    @POST
    @Path("/registrarTipoProduccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int insertTipoProduccion(SRITipoProduccion tipoProduccion) {
        return tipoProduccionBusiness.Insertar(tipoProduccion);
    }
    
     @PUT
    @Path("/updateTipoProduccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateTipoProduccion(SRITipoProduccion tipoProduccion) {
        return tipoProduccionBusiness.Update(tipoProduccion);
    }
    
 
    @PUT
    @Path("/deleteTipoProduccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteTipoProduccion(SRITipoProduccion tipoProduccion) {
        return tipoProduccionBusiness.Delete(tipoProduccion);
    }
    
  
    
  
    
   
}