/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioHome;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
import java.util.logging.Logger;
import javax.ws.rs.PathParam;

/**
 *
 * @author innnovacis
 */
@Path("/usuarios")
@RequestScoped
public class UsuarioRestServices {

    @Inject
    private Logger log;

    @Inject
    private IUsuarioBusiness usuarioBusiness;
    
    @POST
    @Path("/paginacionUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionUsuario(SRIPaginacionObject entidad) {
        
        int total = usuarioBusiness.GetTotalPaginacion(entidad);
        List<SRIUsuarioPersona> lista = usuarioBusiness.GetPagina(entidad);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
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
    public Response saveUsuario(SRIUsuarioPersona usuarioPersona) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        int respuesta = -1;
        try{
            respuesta = usuarioBusiness.InsertarUsuarioPersona(usuarioPersona);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Registrar Usuario : {0}", usuarioPersona.toString());
        }catch(Exception ex){
            log.log(Level.INFO, "Registrar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuarioPersona.toString()});
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
        }
        return builder.build();
    }
    
    @PUT
    @Path("/updateUsuarioPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuarioPersona(SRIUsuarioPersona usuarioPersona) throws GeneralSecurityException, IOException {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        int respuesta = -1;
        try{
            respuesta = usuarioBusiness.UpdateUsuarioPersona(usuarioPersona);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Registrar Usuario : {0}", usuarioPersona.toString());
        }catch(Exception ex){
            log.log(Level.INFO, "Registrar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuarioPersona.toString()});
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
        }
        return builder.build();        
    }
    
    @PUT
    @Path("/deleteUsuarioPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuarioPersona(SRIUsuarioPersona usuarioPersona) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        int respuesta = -1;
        try{
            respuesta = usuarioBusiness.DeleteUsuarioPersona(usuarioPersona);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Registrar Usuario : {0}", usuarioPersona.toString());
        }catch(Exception ex){
            log.log(Level.INFO, "Registrar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuarioPersona.toString()});
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
        }
        return builder.build();         
    }
    
    
    @PUT
    @Path("/updateUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateUsuario(SRIUsuario usuario) throws GeneralSecurityException, IOException {
        return usuarioBusiness.Update(usuario);
    }
    
    @PUT
    @Path("/deleteUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(SRIUsuario usuario) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        try{
            boolean respuesta = usuarioBusiness.Delete(usuario);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(response);
            log.log(Level.INFO, "Eliminar Usuario : {0}", usuario.toString());
        } catch(Exception ex) {
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
            log.log(Level.INFO, "Eliminar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuario.toString()});
        }
        return builder.build();
    }
        
    @POST
    @Path("/autenticarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(SRIUsuario usuario) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> response = new HashMap<>();
        try {
            SRIUsuarioLogin respuesta = usuarioBusiness.AutenticarUsuario(usuario);
            response.put("body", respuesta);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "Autenticar Usuario : {0}", usuario.toString());
        } catch(Exception ex) {
            response.put("body", ex.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response);
            log.log(Level.INFO, "Autenticar Usuario : {0}{1}", new Object[]{ex.getMessage(), usuario.toString()});
        }
        return builder.build();
    }
    
    @GET
    @Path("getByIdUsuario/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetByIdUsuario(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIUsuario body = null;
        try {
            body = usuarioBusiness.GetByIdUsuario(id);
            respuesta.put("body", body);
            if (body == null) {
                builder = Response.status(Response.Status.NOT_FOUND).entity(respuesta);
                log.log(Level.INFO, "getUsuarioById - Nulo : {0}");
            } else {
                builder = Response.status(Response.Status.OK).entity(respuesta);
                log.log(Level.INFO, "getUsuarioById - Success : {0}", body.toString());
            }
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetUsuarioById - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @GET
    @Path("GetActoresByIdUsuario/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetActoresByIdUsuario(@PathParam("id") int id) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIFlujoActor> body = null;
        try {
            body = usuarioBusiness.GetActoresByIdUsuario(id);
            respuesta.put("body", body);
            if (body == null) {
                builder = Response.status(Response.Status.NOT_FOUND).entity(respuesta);
                log.log(Level.INFO, "GetActoresByIdUsuario - Nulo : {0}");
            } else {
                builder = Response.status(Response.Status.OK).entity(respuesta);
                log.log(Level.INFO, "GetActoresByIdUsuario - Success : {0}", body.toString());
            }
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActoresByIdUsuario - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/GetUsuariosColor")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetUsuariosColor(SRIPaginacionObject entidad) {
        
        int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
        List<SRIUsuarioColor> lista = usuarioBusiness.GetUsuariosColor(entidad);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/GetTotalDocentesActivosInactivos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetTotalDocentesActivosInactivos() {
        SRIDocenteActivosInactivos docenteActivosInactivos = usuarioBusiness.GetTotalDocentesActivosInactivos();
        Map<String, Object> response = new HashMap<>();
        response.put("body", docenteActivosInactivos);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(response);
        return builder.build();
    }
    
    @GET
    @Path("GetTotalActivosInactivosByFacultad/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetTotalActivosInactivosByFacultad(@PathParam("id") int id){
        SRIDocenteActivosInactivos docenteActivosInactivos = usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(id);
        Map<String, Object> response = new HashMap<>();
        response.put("body", docenteActivosInactivos);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(response);
        return builder.build();
    }
    
    @GET
    @Path("GetUsuarioHome/{idUsuario:[0-9][0-9]*}/{idUsuarioDirector:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUsuarioHome(@PathParam("idUsuario") int idUsuario, @PathParam("idUsuarioDirector") int idUsuarioDirector) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        List<SRIUsuarioHome> body = null;
        try {
            body = usuarioBusiness.GetUsuarioHome(idUsuario, idUsuarioDirector);
            respuesta.put("body", body);
            if (body == null) {
                builder = Response.status(Response.Status.NOT_FOUND).entity(respuesta);
                log.log(Level.INFO, "GetUsuarioHome - Nulo : {0}");
            } else {
                builder = Response.status(Response.Status.OK).entity(respuesta);
                log.log(Level.INFO, "GetUsuarioHome - Success : {0}", body.toString());
            }
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetUsuarioHome - Error : {0}", ex.getMessage());
        }
        return builder.build();
    }
    
    @POST
    @Path("/descargarPdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarPdf(SRIPaginacionObject entidad) {
              
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> lista = usuarioBusiness.GetUsuariosColor(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            for(int i = 0; i < lista.size(); i++){
                listaObjetosSend.add(lista.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFrom2( nombreColumnas.length,
                    nombreColumnas, "Home Director de Unidad",listaObjetosSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento.pdf")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.pdf")
                .build();
    }
    
    @POST
    @Path("/descargarExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarExcel(SRIPaginacionObject object) {
        
        return null;
    }
    
    
    @POST
    @Path("/descargarUsuariosPdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarUsuariosPdf(SRIPaginacionObject entidad) {
        List<SRIUsuarioPersona> lista = usuarioBusiness.GetPagina(entidad);
        return null;
    }
    
    @POST
    @Path("/descargarUsuariosExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarUsuariosExcel(SRIPaginacionObject entidad) {
        List<SRIUsuarioPersona> lista = usuarioBusiness.GetPagina(entidad);
        return null;
    }
}
