/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ISemestreBusiness;
import com.innnovacis.unsa.model.SRISemestre;
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
@Path("/semestre")
@RequestScoped
public class SemestreRestService {

    @Inject
    private ISemestreBusiness semestreBusiness;
    
    @GET
    @Path("/listarSemestres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRISemestre> GetAll(){
        return semestreBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarSemestres")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveSemestre(SRISemestre semestre){
        return semestreBusiness.Insertar(semestre);
    }
    
    @PUT
    @Path("/updateSemestres")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateSemestre(SRISemestre semestre){
        return semestreBusiness.Update(semestre);
    }
    
    @PUT
    @Path("/deleteSemestres")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteSemestre(SRISemestre semestre){
        return semestreBusiness.Delete(semestre);
    }
}
