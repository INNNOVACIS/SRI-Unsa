/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IRolBusiness;
import com.innnovacis.unsa.model.SRIRol;
import java.util.List;
import javax.ejb.Stateless;
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
@Path("/roles")
@RequestScoped
public class RolRestService {

    @Inject
    private IRolBusiness rolBusiness;
    
    @GET
    @Path("/listarRoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIRol> GetAll(){
        return rolBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarRoles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveRol(SRIRol rol){
        return rolBusiness.Insertar(rol);
    }
    
    @PUT
    @Path("/updateRoles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateRol(SRIRol rol){
        return rolBusiness.Update(rol);
    }
    
    @PUT
    @Path("/deleteRoles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteRol(SRIRol rol){
        return rolBusiness.Delete(rol);
    }
}
