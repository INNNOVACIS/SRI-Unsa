/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.borrar.ArchivoRepository;
import com.innnovacis.unsa.modelborrar.ArchivoData;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
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

//    @Inject
//    private IArchivoBusiness 
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/home/innnovacis/files/";
    
    @Inject
    private ArchivoRepository archivoRepository;
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarArchivo(@PathParam("id") int id) throws FileNotFoundException, IOException, SQLException {
        return archivoRepository.descargarArchivo(id);
    }
    
    @GET
    @Path("/listarArchivos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArchivoData> getArchivos() {
        return archivoRepository.getArchivos();
    }
    
    @POST
    @Path("/actualizarArchivos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response actualizarArchivo(MultipartFormDataInput input) throws SQLException           
    {
        String fileName = "";

        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inPart = formParts.get("file");
//        int idArchivo = formParts.get("idArchivo");
        
        for (InputPart inputPart : inPart) {
            try {                
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                fileName = parseFileName(headers);
                InputStream istream = inputPart.getBody(InputStream.class,null);
                archivoRepository.actualizarArchivo(istream, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String output = "File saved to server location : " + fileName;        

        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) throws SQLException           
    {
        String fileName = "";

        Map<String, List<InputPart>> formParts = input.getFormDataMap();

        List<InputPart> inPart = formParts.get("file");
        System.out.println("lista de inputPart :: " + inPart.size());
        
        for (InputPart inputPart : inPart) {

            try {                
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                fileName = parseFileName(headers);
                InputStream istream = inputPart.getBody(InputStream.class,null);
//                fileName = SERVER_UPLOAD_LOCATION_FOLDER + fileName;
                archivoRepository.saveFile(istream, fileName);
//                saveFile(istream,fileName);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String output = "File saved to server location : " + fileName;        

        return Response.status(200).entity(output).build();
    }
    
    // Parse Content-Disposition header to get the original file name
    private String parseFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"","");

                return fileName;
            }
        }
        return "randomName";
    }

    // save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {        
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                    outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
