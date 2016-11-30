/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.business.IArchivoBusiness;
import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.util.Convert;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author innnovacis
 */
@Path("/files")
@RequestScoped
public class ArchivosRestServices {

    @Inject
    private IArchivoBusiness archivoBusiness;
    
    @Inject
    private Convert convert;
    
    @POST
    @Path("/paginacionArchivos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionPrivilegios(SRIPaginacionObject object) {
        int total = archivoBusiness.GetTotalPaginacion(object);
        List<SRIArchivoUtil> lista = archivoBusiness.GetPagina(object);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarArchivo(@PathParam("id") int id) throws FileNotFoundException, IOException, SQLException {
        return archivoBusiness.descargarArchivo(id);
    }
    
    @GET
    @Path("/listarArchivos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIArchivoUtil> getArchivos() {
        return archivoBusiness.GetAll();
    }
    
    @GET
    @Path("/listar/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRIArchivoUtil> getArchivosById(@PathParam("id") int id) {
        return archivoBusiness.GetArchivosById(id);
    }
    
    @POST
    @Path("/actualizarArchivos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response actualizarArchivo(MultipartFormDataInput input) throws SQLException           
    {
        SRIArchivo entidad = new SRIArchivo();
        String fileName = "";

        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inPart = formParts.get("file");
        
        for (InputPart inputPart : inPart) {
            try {                
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                fileName = convert.parseFileName(headers);
                InputStream istream = inputPart.getBody(InputStream.class,null);
                archivoBusiness.Update(entidad);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String output = "File saved to server location : " + fileName;        

        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("/subirArchivos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response SubirArchivo(MultipartFormDataInput input){
        
        SRIArchivo archivo = new SRIArchivo();
        String fileName = "";
        int idPlanificacion = 0;
        
        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inPart = formParts.get("file");
        List<InputPart> inId = formParts.get("idPlanificacion");
        
        for (InputPart inputPart : inId) {
            try {                
                InputStream istream = inputPart.getBody(InputStream.class,null);
                archivo.setNIdPlanificacion(convert.InputStreamToInt(istream));
            } catch (IOException e) {
            }
        }
        
        for (InputPart inputPart : inPart) {
            try {                
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                InputStream istream = inputPart.getBody(InputStream.class,null);
                
                archivo.setSNombreArchivo(convert.parseFileName(headers));
                archivo.setBlobArchivo(convert.InputStreamToBlob(istream));
                archivoBusiness.Insertar(archivo);
            } catch (IOException e) {
            }
        }
        
        String output = "File saved to server location : " + fileName; 
        return Response.status(200).entity(output).build();
    }
    
}
