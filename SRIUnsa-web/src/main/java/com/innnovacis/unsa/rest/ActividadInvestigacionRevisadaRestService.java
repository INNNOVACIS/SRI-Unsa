/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionRevisadaBusiness;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacionRevisada")
@RequestScoped
public class ActividadInvestigacionRevisadaRestService {

    @Inject
    private IActividadInvestigacionRevisadaBusiness actividadRevisadaBusiness;
    
    @GET
    @Path("/listarActividadInvestigacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIActividadGeneralPaginacion> GetAll(){
        return actividadRevisadaBusiness.GetAll();
    }
    
    @POST
    @Path("/actividadesPaginacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuario(SRIPaginacion entidad) {
        
        List<SRIActividadGeneralPaginacion> lista = actividadRevisadaBusiness.GetPaginacion(entidad);
        int total = actividadRevisadaBusiness.GetTotalPaginacion(entidad);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
}
