/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoNivelBusiness;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.model.SRITipoNivel;
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
@Path("/tipoNivel")
@RequestScoped
public class TipoNivelRestService {

    @Inject
    private ITipoNivelBusiness tipoNivelBusiness;
    
    @POST
    @Path("/paginacionTipoNivel")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionTipoNivel(SRIPaginacionObject object) {
        int total = tipoNivelBusiness.GetTotalPaginacion(object);
        List<SRITipoNivel> lista = tipoNivelBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
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
