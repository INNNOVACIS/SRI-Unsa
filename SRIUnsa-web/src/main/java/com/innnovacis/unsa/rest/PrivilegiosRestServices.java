/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IPrivilegioBusiness;
import com.innnovacis.unsa.model.SRIPrivilegio;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/privilegios")
@RequestScoped
public class PrivilegiosRestServices {

    @Inject
    private IPrivilegioBusiness privilegioBusiness;
    
    @POST
    @Path("/paginacionPrivilegios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionPrivilegios(SRIPaginacionObject object) {
        int total = privilegioBusiness.GetTotalPaginacion(object);
        List<SRIPrivilegio> lista = privilegioBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarRolPrivilegios/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIPrivilegio> GetPrivilegiosByIdRol(@PathParam("id") int id){
        return privilegioBusiness.GetPrivilegiosByIdRol(id);
    }
    
    @GET
    @Path("/listarPrivilegios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIPrivilegio> GetAll(){
        return privilegioBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarPrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SavePrivilegio(SRIPrivilegio privilegio){
        return privilegioBusiness.Insertar(privilegio);
    }
    
    @PUT
    @Path("/updatePrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdatePrivilegio(SRIPrivilegio privilegio){
        return privilegioBusiness.Update(privilegio);
    }
    
    @PUT
    @Path("/deletePrivilegios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeletePrivilegio(SRIPrivilegio privilegio){
        return privilegioBusiness.Delete(privilegio);
    }
}
