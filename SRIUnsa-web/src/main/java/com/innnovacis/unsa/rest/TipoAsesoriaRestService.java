/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ITipoAsesoriaBusiness;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.model.SRITipoAsesoria;
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
@Path("/tipoAsesoria")
@RequestScoped
public class TipoAsesoriaRestService {

    @Inject
    private ITipoAsesoriaBusiness tipoAsesoriaBusiness;
    
    @POST
    @Path("/paginacionTipoAsesoria")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionPrivilegios(SRIPaginacionObject object) {
        int total = tipoAsesoriaBusiness.GetTotalPaginacion(object);
        List<SRITipoAsesoria> lista = tipoAsesoriaBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }

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
