/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IEstructuraAreaInvestigacionBusiness;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
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
@Path("/areaInvestigacion")
@RequestScoped
public class EstructuraAreaInvestigacionRestService {

    @Inject
    private IEstructuraAreaInvestigacionBusiness areaInvestigacionBusiness;
    
    @POST
    @Path("/paginacionAreaInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionAreaInvestigacion(SRIPaginacionObject object) {
        int total = areaInvestigacionBusiness.GetTotalPaginacion(object);
        List<SRIEstructuraAreaInvestigacion> lista = areaInvestigacionBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarAreaInvestigaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIEstructuraAreaInvestigacion> GetAll(){
        return areaInvestigacionBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarAreaInvestigaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveAreaInvestigacion(SRIEstructuraAreaInvestigacion areaInvestigacion){
        return areaInvestigacionBusiness.Insertar(areaInvestigacion);
    }
    
    @PUT
    @Path("/updateAreaInvestigaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateAreaInvestigacion(SRIEstructuraAreaInvestigacion areaInvestigacion){
        return areaInvestigacionBusiness.Update(areaInvestigacion);
    }
    
    @PUT
    @Path("/deleteAreaInvestigaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteAreaInvestigacion(SRIEstructuraAreaInvestigacion areaInvestigacion){
        return areaInvestigacionBusiness.Delete(areaInvestigacion);
    }
}
