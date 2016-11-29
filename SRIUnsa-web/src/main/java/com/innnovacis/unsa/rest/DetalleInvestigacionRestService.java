/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IDetalleInvestigacionFlujoBusiness;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author innnovacis
 */
@Path("/detalleinvestigacion")
@RequestScoped
public class DetalleInvestigacionRestService {

    @Inject
    private IDetalleInvestigacionFlujoBusiness detalleInvestigacionBusiness;

    
    @POST
    @Path("/registrarDetalleInvestigacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveDetalleInvestigacion(SRIDetalleInvestigacionFlujo entidad){
        return detalleInvestigacionBusiness.Insertar(entidad);
    }
    
}
