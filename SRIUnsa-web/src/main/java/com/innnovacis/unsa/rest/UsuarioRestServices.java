/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIUsuariosPaginacion;
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
@Path("/usuarios")
@RequestScoped
public class UsuarioRestServices {

    @Inject
    private IUsuarioBusiness usuarioBusiness;
    
    @POST
    @Path("/paginacionUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuario(SRIUsuariosPaginacion usuario) {
        
        List<SRIUsuario> lista = usuarioBusiness.GetPagina(usuario);

        Response.ResponseBuilder builder = null;
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", 11);
        responseObj.put("lista", lista);
        builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIUsuario> getUsuarios() {
        
        return usuarioBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int saveUsuario(SRIUsuario usuario) {
        System.out.println("USUARIO :: " + usuario);
        return usuarioBusiness.Insertar(usuario);
    }
    
    @PUT
    @Path("/updateUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateUsuario(SRIUsuario usuario) {
        
        return usuarioBusiness.Update(usuario);
    }
    
    @POST
    @Path("/autenticarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SRIUsuario autenticarUsuario(SRIUsuario usuario) {        
        usuarioBusiness.Autenticar(usuario);
        return usuario;
    }
    
    @PUT
    @Path("/deleteUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteUsuario(SRIUsuario usuario) {
        return usuarioBusiness.Delete(usuario);
    }
    
    @GET
    @Path("/listarPrueba")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario() {   
        List<SRIUsuario> lista = usuarioBusiness.GetAll();
        
        Response.ResponseBuilder builder = null;
        
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("Total", 11);
        responseObj.put("lista", lista);
        builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
}