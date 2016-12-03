/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.util.SRIPaginacion;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @POST
    @Path("/actividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesRevisadas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(actividadInvestigacionBusiness.GetActividadesRevisadas(entidad));
        return builder.build();
    }
    
}
