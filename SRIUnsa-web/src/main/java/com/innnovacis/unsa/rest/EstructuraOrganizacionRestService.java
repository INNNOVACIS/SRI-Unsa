/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IEstructuraOrganizacionBusiness;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author innnovacis
 */
@Path("/estructuraOrganizacion")
@RequestScoped
public class EstructuraOrganizacionRestService {

    @Inject
    private IEstructuraOrganizacionBusiness estructuraOrganizacionBusiness;
    
    @GET
    @Path("/listarEstructuraOrganizaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIEstructuraOrganizacion> GetAll(){
        return estructuraOrganizacionBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarEstructuraOrganizaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveAreaInvestigacion(SRIEstructuraOrganizacion estructuraOrganizacion){
        return estructuraOrganizacionBusiness.Insertar(estructuraOrganizacion);
    }
    
    @PUT
    @Path("/updateEstructuraOrganizaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateAreaInvestigacion(SRIEstructuraOrganizacion estructuraOrganizacion){
        return estructuraOrganizacionBusiness.Update(estructuraOrganizacion);
    }
    
    @PUT
    @Path("/deleteEstructuraOrganizaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteAreaInvestigacion(SRIEstructuraOrganizacion estructuraOrganizacion){
        return estructuraOrganizacionBusiness.Delete(estructuraOrganizacion);
    }
}
