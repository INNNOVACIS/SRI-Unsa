/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.borrar;

import com.innnovacis.unsa.modelborrar.Archivo;
import com.innnovacis.unsa.modelborrar.ArchivoData;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.rowset.serial.SerialBlob;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author innnovacis
 */
public class ArchivoRepository {
    private static final String SAVE_FOLDER = "/home/will/Download/Server/";
    
    @Inject
    private Logger log;
    @Inject
    private EntityManager em;
    
    @Inject
    private Event<Archivo> archivoEventSrc;
    

    public List<ArchivoData> getArchivos(){
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ArchivoData> criteria = cb.createQuery(ArchivoData.class);
        Root<ArchivoData> archivoRoot = criteria.from(ArchivoData.class);
        criteria.select(archivoRoot);     
        System.out.println("select =====> " + em.createQuery(criteria).getResultList());
        List<ArchivoData> lstArchivo = em.createQuery(criteria).getResultList();
        return lstArchivo; 
    }
    
    public void actualizarArchivo(InputStream inStream, String name){
        
    }
    
    public void saveFile(InputStream inStream, String name) throws SQLException, IOException{
        
        Blob blob = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        try {
            while ((read = inStream.read(buffer)) != -1)
                output.write(buffer, 0, read);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        output.flush();
        byte[] contents = output.toByteArray();
        blob = new SerialBlob(contents);
        
        Archivo archivo = new Archivo();
        archivo.setTitulo(name);
//        archivo.setFile(blob);
        
        em.merge(archivo);
        archivoEventSrc.fire(archivo);
    }

    public Response descargarArchivo (int id) throws SQLException, FileNotFoundException, IOException{
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Archivo> criteria = cb.createQuery(Archivo.class);
        Root<Archivo> archivoRoot = criteria.from(Archivo.class);
        criteria.select(archivoRoot);
        criteria.where(cb.equal(archivoRoot.get("id"),id));
        
        Archivo file = em.createQuery(criteria).getSingleResult();
        System.out.println("blob ===> " + file.getFile().length());               
        
        int blobLength = (int) file.getFile().length();  
        byte[] blobAsBytes = file.getFile().getBytes(1, blobLength);
 
        return Response
                .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition",file.getTitulo())
                .build();

    }
    
}
