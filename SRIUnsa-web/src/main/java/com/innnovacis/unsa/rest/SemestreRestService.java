/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.ISemestreBusiness;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.model.SRISemestre;
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
@Path("/semestre")
@RequestScoped
public class SemestreRestService {

    @Inject
    private ISemestreBusiness semestreBusiness;
    
    @POST
    @Path("/paginacionSemestre")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionSemestre(SRIPaginacionObject object) {
        int total = semestreBusiness.GetTotalPaginacion(object);
        List<SRISemestre> lista = semestreBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
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
