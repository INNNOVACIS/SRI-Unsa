/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.GenerateExcel;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocentesActividades;
import com.innnovacis.unsa.util.SRIDocentesActivosInactivosFacultad;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRITotalTipoActividad;
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
    
    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
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
    @Path("/GetTotalDocentesActivosInactivos/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetTotalDocentesActivosInactivos(@PathParam("id") int idSemestre) {
        SRIDocenteActivosInactivos docenteActivosInactivos = usuarioBusiness.GetTotalDocentesActivosInactivos(idSemestre);
        Map<String, Object> response = new HashMap<>();
        response.put("body", docenteActivosInactivos);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(response);
        return builder.build();
    }
    
    @GET
    @Path("GetTotalActivosInactivosByFacultad/{id:[0-9][0-9]*}/{idSemestre:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetTotalActivosInactivosByFacultad(@PathParam("id") int id,@PathParam("idSemestre") int idSemestre){
        SRIDocenteActivosInactivos docenteActivosInactivos = usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(id,idSemestre);
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
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);
            
            // Usuarios activos e inactivos
            SRIDocenteActivosInactivos docenteActivosInactivos = 
                    usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(entidad.getIdFacultad(),entidad.getIdSemestre());
            
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness
                            .GetActivosInactivosByFacultad(entidad.getIdFacultad(), 0);
            
            //Transformando las vistas
            ArrayList<ArrayList<String>> listaUsuariosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++){
                listaUsuariosSend.add(listaUsuarios.get(i).getArrayDatos());
            }            
            String[] listaUsuariosSendNombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<Integer>> docenteActivosInactivosSend = new ArrayList<>();            
            docenteActivosInactivosSend.add(docenteActivosInactivos.getArrayDatos());
            String[] docenteActivosInactivosNombreColumnas = docenteActivosInactivos.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFromPDFMultiplesTablas( "Resumen Actividades",
                    listaUsuariosSendNombreColumnas.length,listaUsuariosSendNombreColumnas,listaUsuariosSend,
                    totalTipoActividadesSendNombreColumnas.length,totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend,
                    docenteActivosInactivosNombreColumnas.length,docenteActivosInactivosNombreColumnas,docenteActivosInactivosSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documentoModelo.pdf")
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
    @Path("/descargarHomeDirectorUnidadPDF")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDirectorUnidadPDF(SRIPaginacionObject entidad) {
              
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);
            
            // Usuarios activos e inactivos
            SRIDocenteActivosInactivos docenteActivosInactivos = 
                    usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(entidad.getIdFacultad(),entidad.getIdSemestre());
            
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness.GetTotalActivosInactivosByDepartamento(entidad.getIdFacultad(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
//                    actividadInvestigacionBusiness
//                            .GetActivosInactivosByFacultad(entidad.getIdFacultad());
            
            //Transformando las vistas
            ArrayList<ArrayList<String>> listaUsuariosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++){
                listaUsuariosSend.add(listaUsuarios.get(i).getArrayDatos());
            }            
            String[] listaUsuariosSendNombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<Integer>> docenteActivosInactivosSend = new ArrayList<>();            
            docenteActivosInactivosSend.add(docenteActivosInactivos.getArrayDatos());
            String[] docenteActivosInactivosNombreColumnas = docenteActivosInactivos.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFromPDFMultiplesTablas( "Resumen Actividades",
                    listaUsuariosSendNombreColumnas.length,listaUsuariosSendNombreColumnas,listaUsuariosSend,
                    totalTipoActividadesSendNombreColumnas.length,totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend,
                    docenteActivosInactivosNombreColumnas.length,docenteActivosInactivosNombreColumnas,docenteActivosInactivosSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documentoModelo.pdf")
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
    @Path("/descargarHomeDirectorDepartamentoPDF")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDirectorDepartamentoPDF(SRIPaginacionObject entidad) {
              
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);
            
            
             SRIDocenteActivosInactivos docenteActivosInactivos = new SRIDocenteActivosInactivos();
            // Usuarios activos e inactivos
            List<SRIDocentesActivosInactivosFacultad> lstDocenteActivosInactivos = 
                    actividadInvestigacionBusiness.GetTotalActivosInactivosHomeDepartamento(entidad.getIdDepartamento(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
            
            docenteActivosInactivos.setNActivos(lstDocenteActivosInactivos.get(0).getNActivos());
            docenteActivosInactivos.setNInactivos(lstDocenteActivosInactivos.get(0).getNInactivos());
            
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness.GetTotalActivosInactivosHomeDepartamento(entidad.getIdDepartamento(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
//                    actividadInvestigacionBusiness
//                            .GetActivosInactivosByFacultad(entidad.getIdFacultad());
            
            //Transformando las vistas
            ArrayList<ArrayList<String>> listaUsuariosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++){
                listaUsuariosSend.add(listaUsuarios.get(i).getArrayDatos());
            }            
            String[] listaUsuariosSendNombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<Integer>> docenteActivosInactivosSend = new ArrayList<>();            
            docenteActivosInactivosSend.add(docenteActivosInactivos.getArrayDatos());
            String[] docenteActivosInactivosNombreColumnas = docenteActivosInactivos.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFromPDFMultiplesTablas( "Resumen Actividades",
                    listaUsuariosSendNombreColumnas.length,listaUsuariosSendNombreColumnas,listaUsuariosSend,
                    totalTipoActividadesSendNombreColumnas.length,totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend,
                    docenteActivosInactivosNombreColumnas.length,docenteActivosInactivosNombreColumnas,docenteActivosInactivosSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documentoModelo.pdf")
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
    public Response descargarExcel(SRIPaginacionObject entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);            
            // Usuarios activos e inactivos
            SRIDocenteActivosInactivos docenteActivosInactivos = 
                    usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(entidad.getIdFacultad(),entidad.getIdSemestre());
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness
                            .GetActivosInactivosByFacultad(entidad.getIdFacultad(), 0);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++) {
                listaObjetosSend.add(listaUsuarios.get(i).getArrayDatos());
            }
            String[] nombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GenerateExcel generadorExcel =  new GenerateExcel();            
            byte[] blobAsBytes = generadorExcel.getArrayByteFrom2(respuesta, nombreColumnas.length,
                    nombreColumnas, "Actividades de Investigación Generadas",listaObjetosSend,
                    docenteActivosInactivos,totalTipoActividadesSendNombreColumnas.length,
                    totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,
                    facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento.xlsx")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.xlsx")
                .build();
    }
    
    @POST
    @Path("/descargarHomeDirectorUnidadExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDirectorUnidadExcel(SRIPaginacionObject entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);
            
            // Usuarios activos e inactivos
            SRIDocenteActivosInactivos docenteActivosInactivos = 
                    usuarioBusiness.GetTotalDocentesActivosInactivosByFacultad(entidad.getIdFacultad(),entidad.getIdSemestre());
            
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness.GetTotalActivosInactivosByDepartamento(entidad.getIdFacultad(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
//                    actividadInvestigacionBusiness
//                            .GetActivosInactivosByFacultad(entidad.getIdFacultad());
            
            //Transformando las vistas
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++){
                listaObjetosSend.add(listaUsuarios.get(i).getArrayDatos());
            }            
            String[] nombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<Integer>> docenteActivosInactivosSend = new ArrayList<>();            
            docenteActivosInactivosSend.add(docenteActivosInactivos.getArrayDatos());
            String[] docenteActivosInactivosNombreColumnas = docenteActivosInactivos.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GenerateExcel generadorExcel =  new GenerateExcel();            
            byte[] blobAsBytes = generadorExcel.getArrayByteFrom2(respuesta, nombreColumnas.length,
                    nombreColumnas, "Actividades de Investigación Generadas",listaObjetosSend,
                    docenteActivosInactivos,totalTipoActividadesSendNombreColumnas.length,
                    totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,
                    facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento.xlsx")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.xlsx")
                .build();
    }
    
    @POST
    @Path("/descargarHomeDirectorDepartamentoExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDirectorDepartamentoExcel(SRIPaginacionObject entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // Establecemos un rango grande para traer todos los elementos
            entidad.setRango(2000);
            int total = usuarioBusiness.GetTotalUsuariosColor(entidad);
            List<SRIUsuarioColor> listaUsuarios = usuarioBusiness.GetUsuariosColor(entidad);
            
            
             SRIDocenteActivosInactivos docenteActivosInactivos = new SRIDocenteActivosInactivos();
            // Usuarios activos e inactivos
            List<SRIDocentesActivosInactivosFacultad> lstDocenteActivosInactivos = 
                    actividadInvestigacionBusiness.GetTotalActivosInactivosHomeDepartamento(entidad.getIdDepartamento(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
            
            docenteActivosInactivos.setNActivos(lstDocenteActivosInactivos.get(0).getNActivos());
            docenteActivosInactivos.setNInactivos(lstDocenteActivosInactivos.get(0).getNInactivos());
            
            // Obtenemos el total de actividades por tipo
            List<SRITotalTipoActividad>  totalTipoActividades = 
                    //actividadInvestigacionBusiness.GetTotalActividadesByTipoActividad();
                    actividadInvestigacionBusiness.GetTotalActividadesByTipoActividadFacultad(entidad.getIdFacultad(), entidad.getIdSemestre());
            
            // Lista  de las facultades
            List<SRIDocentesActivosInactivosFacultad>  facultadesPorTipoActividades =
                    actividadInvestigacionBusiness.GetTotalActivosInactivosHomeDepartamento(entidad.getIdDepartamento(), entidad.getIdTipoInvestigacion(), entidad.getIdSemestre());
//                    actividadInvestigacionBusiness
//                            .GetActivosInactivosByFacultad(entidad.getIdFacultad());
            
            //Transformando las vistas
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<>();            
            for(int i = 0; i < listaUsuarios.size(); i++){
                listaObjetosSend.add(listaUsuarios.get(i).getArrayDatos());
            }            
            String[] nombreColumnas = SRIUsuarioColor.getArrayHeaders();
            
            ArrayList<ArrayList<Integer>> docenteActivosInactivosSend = new ArrayList<>();            
            docenteActivosInactivosSend.add(docenteActivosInactivos.getArrayDatos());
            String[] docenteActivosInactivosNombreColumnas = docenteActivosInactivos.getArrayHeaders();
            
            ArrayList<ArrayList<String>> totalTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < totalTipoActividades.size(); i++){
                totalTipoActividadesSend.add(totalTipoActividades.get(i).getArrayDatos());
            }            
            String[] totalTipoActividadesSendNombreColumnas = SRITotalTipoActividad.getArrayHeaders();
            
            ArrayList<ArrayList<String>> facultadesPorTipoActividadesSend = new ArrayList<>();            
            for(int i = 0; i < facultadesPorTipoActividades.size(); i++){
                facultadesPorTipoActividadesSend.add(facultadesPorTipoActividades.get(i).getArrayDatos());
            }            
            String[] facultadesPorTipoActividadesSendNombreColumnas = SRIDocentesActivosInactivosFacultad.getArrayHeaders();
            
            GenerateExcel generadorExcel =  new GenerateExcel();            
            byte[] blobAsBytes = generadorExcel.getArrayByteFrom2(respuesta, nombreColumnas.length,
                    nombreColumnas, "Actividades de Investigación Generadas",listaObjetosSend,
                    docenteActivosInactivos,totalTipoActividadesSendNombreColumnas.length,
                    totalTipoActividadesSendNombreColumnas,totalTipoActividadesSend,
                    facultadesPorTipoActividadesSendNombreColumnas.length,
                    facultadesPorTipoActividadesSendNombreColumnas,facultadesPorTipoActividadesSend);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "ReporteActividades.xlsx")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.xlsx")
                .build();
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
    
    @GET
    @Path("enviarCodigo/{idUsuario:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean enviarCodigo(@PathParam("idUsuario") int idUsuario){
        boolean respuesta = usuarioBusiness.enviarCodigo(idUsuario);
        return respuesta;
    }
    
    @POST
    @Path("/verificarCodigo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SRIUsuario verificarCodigo(SRIUsuario usuario) {
        SRIUsuario respuesta = usuarioBusiness.verificarCodigo(usuario);
        return respuesta;
    }
    
    @POST
    @Path("/imprimirDocentesInvestigando")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response imprimirDocentesInvestigando(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetDocentesActivos(entidad);
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            ArrayList<SRIDocentesActividades> listaObjetos = (ArrayList<SRIDocentesActividades>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIDocentesActividades.getArrayHeaders();
            
            SRIDocente docenteReporte = usuarioBusiness.GetDocenteReporte(entidad.getIdUsuario());
            
            String universidad = "UNIVERSIDAD NACIONAL DE SAN AGUSTÍN DE AREQUIPA";
            String vicerrectorado = "VICERRECTORADO DE INVESTIGACIÓN";
            String facultad = docenteReporte.getFacultad();
            String departamento = docenteReporte.getDepartamento(); //"DIRECCIÓN DE UNIDAD DE INVESTIGACIÓN";//docenteReporte.getDepartamento();
            String actividades = "Informe de Docentes Investigando";
            String periodo = "Periodo: " + entidad.getFiltro().getSSemestre();
            String docente = "Docente: " + docenteReporte.getNombres() + " " + docenteReporte.getApellidos();
            String dni = "DNI: " + docenteReporte.getDni();
            
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Informe de Docentes con Actividad",listaObjetosSend,
                    universidad, vicerrectorado,facultad, departamento, actividades,
                    periodo, docente, dni);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "Docentes con Actividad.pdf")
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
    @Path("/imprimirDocentesNoInvestigando")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response imprimirDocentesNoInvestigando(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetDocentesInactivos(entidad);
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            ArrayList<SRIDocentesActividades> listaObjetos = (ArrayList<SRIDocentesActividades>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIDocentesActividades.getArrayHeaders();
            
            SRIDocente docenteReporte = usuarioBusiness.GetDocenteReporte(entidad.getIdUsuario());
            
            String universidad = "UNIVERSIDAD NACIONAL DE SAN AGUSTÍN DE AREQUIPA";
            String vicerrectorado = "VICERRECTORADO DE INVESTIGACIÓN";
            String facultad = docenteReporte.getFacultad();
            String departamento = docenteReporte.getDepartamento();//"DIRECCIÓN DE UNIDAD DE INVESTIGACIÓN";
            String actividades = "Informe de Docentes NO Investigando";
            String periodo = "Periodo: " + entidad.getFiltro().getSSemestre();
            String docente = "Docente: " + docenteReporte.getNombres() + " " + docenteReporte.getApellidos();
            String dni = "DNI: " + docenteReporte.getDni();
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Informe de Docentes NO Investigando",listaObjetosSend,
                    universidad, vicerrectorado,facultad, departamento, actividades,
                    periodo, docente, dni);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "Docentes sin Actividad.pdf")
                    .build();

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(new byte[0], MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "documentovacio.pdf")
                .build();
    }
}
