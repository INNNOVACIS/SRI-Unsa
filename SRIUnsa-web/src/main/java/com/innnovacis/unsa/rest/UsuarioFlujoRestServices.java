/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IUsuarioFlujoBusiness;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.SRIFlujoActorUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioFlujoUtil;
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
@Path("/usuarioflujo")
@RequestScoped
public class UsuarioFlujoRestServices {

    @Inject
    private IUsuarioFlujoBusiness usuarioFlujoBusiness;
    
    @POST
    @Path("/paginacionUsuarioFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuarioFlujo(SRIPaginacionObject object) {
        int total = usuarioFlujoBusiness.GetTotalPaginacion(object);
        List<SRIUsuarioFlujoUtil> lista = usuarioFlujoBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/listarUsuarios/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIFlujoActorUtil> getUsuarioFlujoByIdUsuario(@PathParam("id") int id) {
        return usuarioFlujoBusiness.getUsuarioFlujoByIdUsuario(id);
    }
    
    @GET
    @Path("/deleteUsuarioFlujoById/{idUsuario:[0-9][0-9]*}/{idFlujoActor:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteUsuarioFlujoById(@PathParam("idUsuario") int idUsuario, @PathParam("idFlujoActor") int idFlujoActor) {
//        return usuarioFlujoBusiness.getUsuarioFlujoByIdUsuario(id);
        System.out.println("idUsuario = " + idUsuario + " :: idFlujoActor = " + idFlujoActor);
        return true;
    }
    
    @GET
    @Path("/listarUsuarioFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIUsuarioFlujo> getUsuarioFlujos() {
        return usuarioFlujoBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarUsuarioFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int saveUsuarioFlujo(SRIUsuarioFlujo object) {        
        return usuarioFlujoBusiness.Insertar(object);
    }
    
    @PUT
    @Path("/updateUsuarioFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateUsuarioFlujo(SRIUsuarioFlujo object) {
        return usuarioFlujoBusiness.Update(object);
    }
    
    @PUT
    @Path("/deleteUsuarioFlujo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteUsuarioFlujo(SRIUsuarioFlujo object) {
        return usuarioFlujoBusiness.Delete(object);
    }
    
}