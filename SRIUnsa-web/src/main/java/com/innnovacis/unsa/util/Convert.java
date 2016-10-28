/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.borrar.ArchivoRepository;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Innnovacis
 */
public class Convert {
    
    public Blob InputStreamToBlob(InputStream input){
        
        Blob blob = null;
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            try {
                while ((read = input.read(buffer)) != -1)
                    output.write(buffer, 0, read);
            } catch (IOException ex) {
                Logger.getLogger(ArchivoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            byte[] contents = output.toByteArray();
            blob = new SerialBlob(contents);
        } catch (SQLException ex) {
            Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blob;
    }
    
    // Parse Content-Disposition header to get the original file name
    public String parseFileName(MultivaluedMap<String, String> headers) {

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
    public void guardarFileLocal(InputStream uploadedInputStream,
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
