/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionRevisadaBusiness;
import com.innnovacis.unsa.util.SRIActividadesRevisadas;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacionGenerada")
@RequestScoped
public class ActividadInvestigacionGeneradaRestService {

    @Inject
    private IActividadInvestigacionRevisadaBusiness actividadRevisadaBusiness;
    
    @GET
    @Path("/listarActividadGenerada")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIActividadesRevisadas> GetAll(){
        return actividadRevisadaBusiness.GetAll();
    }
    
    @POST
    @Path("/actividadesPaginacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SRIActividadesRevisadas> paginacionUsuario(SRIPaginacion entidad) {
        System.out.println("paginacion =========> " + entidad);
        return actividadRevisadaBusiness.GetPaginacion(entidad);
    }
    
}
