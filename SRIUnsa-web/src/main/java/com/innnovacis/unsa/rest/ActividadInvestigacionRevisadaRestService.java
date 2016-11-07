/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionRevisadaBusiness;
import com.innnovacis.unsa.util.SRIActividadesRevisadas;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public List<SRIActividadesRevisadas> GetAll(){
        List<SRIActividadesRevisadas> listActividades = actividadRevisadaBusiness.GetAll();
        System.out.println("actividades =====> " + listActividades);
        return listActividades;
    }
    
}
