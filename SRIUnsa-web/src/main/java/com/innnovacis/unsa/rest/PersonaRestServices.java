/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.innnovacis.unsa.business.IPersonaBusiness;
import com.innnovacis.unsa.model.SRIPersona;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;

/**
 *
 * @author innnovacis
 */
@Path("/persona")
@RequestScoped
public class PersonaRestServices {

    @Inject
    private Logger log;

    @Inject
    private IPersonaBusiness personaBusiness;
    
    @GET
    @Path("/getPersonas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIPersona> getPersonas() {
        return personaBusiness.GetAll();
    }
    
    @PUT
    @Path("/updatePersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updatePersona(SRIPersona persona) {
        
            return personaBusiness.updatePersona(persona);        
    }
 
}
