/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.borrar;

import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.modelborrar.Archivo;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.transaction.Transactional;
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
    

    public List<SRIArchivoUtil> getArchivos(){
        List<SRIArchivoUtil> lstArchivoUtil = new ArrayList<SRIArchivoUtil>();
        List<SRIArchivo> olistaRespuesta = em.createNamedQuery("SRIArchivo.GetAll",SRIArchivo.class).getResultList();
        for(SRIArchivo sriArchivo : olistaRespuesta){
            SRIArchivoUtil objArchivoUtil = new SRIArchivoUtil();
            objArchivoUtil.setId(sriArchivo.getNIdArchivo());
            objArchivoUtil.setNombre(sriArchivo.getSNombreArchivo());
            lstArchivoUtil.add(objArchivoUtil);
        }
        
        return lstArchivoUtil; 
    }
    
    public void actualizarArchivo(InputStream inStream, String name){
        
    }
    
    
    @Transactional
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
        
        //output.flush();
        byte[] contents = output.toByteArray();
        blob = new SerialBlob(contents);
        
        Archivo archivo = new Archivo();
        archivo.setTitulo(name);
        archivo.setFile(blob);
        
        em.persist(archivo);
    }

    public Response descargarArchivo (int id) throws SQLException, FileNotFoundException, IOException{
        
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Archivo> criteria = cb.createQuery(Archivo.class);
//        Root<Archivo> archivoRoot = criteria.from(Archivo.class);
//        criteria.select(archivoRoot);
//        criteria.where(cb.equal(archivoRoot.get("id"),id));
//        
//        Archivo file = em.createQuery(criteria).getSingleResult();
        SRIArchivo file = em.createNamedQuery("SRIArchivo.GetById", SRIArchivo.class).setParameter("idEntidad", id).getSingleResult();
        System.out.println("blob ===> " + file.getBlobArchivo().length());               
        
        int blobLength = (int) file.getBlobArchivo().length();  
        byte[] blobAsBytes = file.getBlobArchivo().getBytes(1, blobLength);
 
        return Response
                .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition",file.getSNombreArchivo())
                .build();

    }
    
}
