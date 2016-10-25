/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IRolPrivilegioBusiness;
import com.innnovacis.unsa.model.SRIRolPrivilegio;
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
@Path("/rolPrivilegios")
@RequestScoped
public class RolPrivilegio {

    @Inject
    private IRolPrivilegioBusiness rolPrivilegiosBusiness;
    
    @GET
    @Path("/listarRolPrivilegios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIRolPrivilegio> GetAll(){
        return rolPrivilegiosBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarRolPrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveUsuarioRol(SRIRolPrivilegio rolPrivilegio){
        return rolPrivilegiosBusiness.Insertar(rolPrivilegio);
    }
    
    @PUT
    @Path("/updateRolPrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateUsuarioRol(SRIRolPrivilegio rolPrivilegio){
        return rolPrivilegiosBusiness.Update(rolPrivilegio);
    }
    
    @PUT
    @Path("/deleteRolPrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteUsuarioRol(SRIRolPrivilegio rolPrivilegio){
        return rolPrivilegiosBusiness.Delete(rolPrivilegio);
    }
    
}
