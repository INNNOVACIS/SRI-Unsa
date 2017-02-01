/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.util.GenerateExcel;
import com.innnovacis.unsa.util.GeneratePdf;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/actividadInvestigacionGenerada")
@RequestScoped
public class ActividadInvestigacionGeneradaRestService {

    @Inject
    private Logger log;

    @Inject
    private IActividadInvestigacionBusiness actividadInvestigacionBusiness;
    
    @Inject
    private IUsuarioBusiness usuarioBusiness;

    @POST
    @Path("/enviarInforme")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean EnviarInforme(SRIPaginacion entidad) {
        Map<String, Object> respuesta = new HashMap<>();
        boolean respuesta2 = false;
        try {
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradasHomeDocente(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIActividadGeneralPaginacion.getArrayHeaders();
            
           SRIDocente docenteReporte = usuarioBusiness.GetDocenteReporte(entidad.getIdUsuario());
            
            String universidad = "Universidad Nacional de San Agustín de Arequipa";
            String vicerrectorado = "Vicerrectorado de Investigación";
            String facultad = docenteReporte.getFacultad();
            String departamento = docenteReporte.getDepartamento();
            String actividades = "Informe de Actividades de Investigación";
            String periodo = "Periodo: " + entidad.getFiltro().getSSemestre();
            String docente = "Docente: " + docenteReporte.getNombres() + " " + docenteReporte.getApellidos();
            String dni = "DNI: " + docenteReporte.getDni();
            
            GeneratePdf generador =  new GeneratePdf();            
            byte[] blobAsBytes = generador.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Informe de Actividades de Investigación",listaObjetosSend,
                    universidad, vicerrectorado,facultad, departamento, actividades,
                    periodo, docente, dni);
            
            respuesta2 = actividadInvestigacionBusiness.EnviarInforme(blobAsBytes, entidad);
            
            return respuesta2;

        } catch (Exception ex) {
            Logger.getLogger(ActividadInvestigacionGeneradaRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @POST
    @Path("/actividades")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesGeneradas(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesGeneradas - Success : {0}", entidad.toString());
        } catch (Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesGeneradas - Error : {0}", ex.getMessage());
        }

        return builder.build();
    }

    
    @POST
    @Path("/GetActividadesGeneradasHomeDocente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetActividadesGeneradasHomeDocente(SRIPaginacion entidad) {
        Response.ResponseBuilder builder = null;
        Map<String, Object> respuesta = new HashMap<>();
        try {
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradasHomeDocente(entidad);
            builder = Response.status(Response.Status.OK).entity(respuesta);
            log.log(Level.INFO, "GetActividadesGeneradasHomeDocente - Success : {0}", entidad.toString());
        } catch(Exception ex) {
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage());
            log.log(Level.INFO, "GetActividadesGeneradasHomeDocente - Error : {0}", ex.getMessage());
        }

        return builder.build();
    }

    //@Produces("application/pdf")
    @POST
    @Path("/descargarPdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarPdf(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIActividadGeneralPaginacion.getArrayHeaders();
            
            SRIDocente docenteReporte = usuarioBusiness.GetDocenteReporte(entidad.getIdUsuario());
            
            String universidad = "Universidad Nacional de San Agustín de Arequipa";
            String vicerrectorado = "Vicerrectorado de Investigación";
            String facultad = docenteReporte.getFacultad();
            String departamento = docenteReporte.getDepartamento();
            String actividades = "Informe de Actividades de Investigación";
            String periodo = "Periodo: " + entidad.getFiltro().getSSemestre();
            String docente = "Docente: " + docenteReporte.getNombres() + " " + docenteReporte.getApellidos();
            String dni = "DNI: " + docenteReporte.getDni();
            
            
            GeneratePdf generadorPDF =  new GeneratePdf();            
            byte[] blobAsBytes = generadorPDF.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Actividades de Investigación Generadas",listaObjetosSend,
                    universidad, vicerrectorado,facultad, departamento, actividades,
                    periodo, docente, dni);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "Reporte de Actividades.pdf")
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
    public Response descargarExcel(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradas(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIActividadGeneralPaginacion.getArrayHeaders();
            
            GenerateExcel generadorExcel =  new GenerateExcel();            
            byte[] blobAsBytes = generadorExcel.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Actividades de Investigación Generadas",listaObjetosSend);
            
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
    @Path("/descargarHomeDocentePdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDocentePdf(SRIPaginacion entidad) {
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradasHomeDocente(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIActividadGeneralPaginacion.getArrayHeaders();
            
            SRIDocente docenteReporte = usuarioBusiness.GetDocenteReporte(entidad.getIdUsuario());
            
            String universidad = "Universidad Nacional de San Agustín de Arequipa";
            String vicerrectorado = "Vicerrectorado de Investigación";
            String facultad = docenteReporte.getFacultad();
            String departamento = docenteReporte.getDepartamento();
            String actividades = "Informe de Actividades de Investigación";
            String periodo = "Periodo: " + entidad.getFiltro().getSSemestre();
            String docente = "Docente: " + docenteReporte.getNombres() + " " + docenteReporte.getApellidos();
            String dni = "DNI: " + docenteReporte.getDni();
                       
            GeneratePdf generador =  new GeneratePdf();            
            byte[] blobAsBytes = generador.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Home Docente",listaObjetosSend,
                    universidad, vicerrectorado,facultad, departamento, actividades,
                    periodo, docente, dni);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "documento.pdf")
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
    @Path("/descargarHomeDocenteExcel")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarHomeDocenteExcel(SRIPaginacion entidad) {
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // establecemos como parametro de RANGO 2000 para que al momento de exportar traiga todos
            // los elementos
            entidad.setRango(2000);
            respuesta = actividadInvestigacionBusiness.GetActividadesGeneradasHomeDocente(entidad);
            
            ArrayList<ArrayList<String>> listaObjetosSend = new ArrayList<ArrayList<String>>();
            
            ArrayList<SRIActividadGeneralPaginacion> listaObjetos
                = (ArrayList<SRIActividadGeneralPaginacion>) respuesta.get("lista");
            
            for(int i = 0; i < listaObjetos.size(); i++){
                listaObjetosSend.add(listaObjetos.get(i).getArrayDatos());
            }
            
            String[] nombreColumnas = SRIActividadGeneralPaginacion.getArrayHeaders();
            
            GenerateExcel generadorExcel =  new GenerateExcel();            
            byte[] blobAsBytes = generadorExcel.getArrayByteFrom(respuesta, nombreColumnas.length,
                    nombreColumnas, "Home Docente",listaObjetosSend);
            
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
}
