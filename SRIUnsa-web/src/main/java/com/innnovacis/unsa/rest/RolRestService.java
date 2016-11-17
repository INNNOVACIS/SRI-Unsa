/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IRolBusiness;
import com.innnovacis.unsa.model.SRIRol;
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
@Path("/roles")
@RequestScoped
public class RolRestService {

    @Inject
    private IRolBusiness rolBusiness;
    
    @POST
    @Path("/paginacionRoles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionRol(SRIPaginacionObject object) {
        int total = rolBusiness.GetTotalPaginacion(object);
        List<SRIRol> lista = rolBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
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
