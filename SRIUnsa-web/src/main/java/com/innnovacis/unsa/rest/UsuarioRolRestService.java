/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IUsuarioRolBusiness;
import com.innnovacis.unsa.model.SRIUsuarioRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import com.innnovacis.unsa.util.SRIUsuarioRolesUtil;
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
@Path("/usuarioRoles")
@RequestScoped
public class UsuarioRolRestService {

    @Inject
    private IUsuarioRolBusiness usuarioRolBusiness;
    
    @POST
    @Path("/paginacionUsuarioRol")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuarioRol(SRIPaginacionObject object) {
        int total = usuarioRolBusiness.GetTotalPaginacion(object);
        List<SRIUsuarioRolUtil> lista = usuarioRolBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarUsuarioRoles/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIUsuarioRolesUtil> getUsuarioRolByIdUsuario(@PathParam("id") int id) {
        return usuarioRolBusiness.getUsuarioRolByIdUsuario(id);
    }
    
    @GET
    @Path("/listarUsuarioRol")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIUsuarioRol> GetAll(){
        return usuarioRolBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarUsuarioRol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveUsuarioRol(SRIUsuarioRol usuarioRol){
        return usuarioRolBusiness.Insertar(usuarioRol);
    }
    
    @PUT
    @Path("/updateUsuarioRol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateUsuarioRol(SRIUsuarioRol usuarioRol){
        return usuarioRolBusiness.Update(usuarioRol);
    }
    
    @PUT
    @Path("/deleteUsuarioRol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteUsuarioRol(SRIUsuarioRol usuarioRol){
        return usuarioRolBusiness.Delete(usuarioRol);
    }
}
