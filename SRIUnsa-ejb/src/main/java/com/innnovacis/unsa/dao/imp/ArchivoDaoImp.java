
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IArchivoDao;
import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Dependent
public class ArchivoDaoImp implements IArchivoDao {
//archivo
    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIArchivo  Insert(SRIArchivo entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIArchivo Update(SRIArchivo entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIArchivo entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIArchivo GetById(int idEntidad) {
        SRIArchivo entidad = em.createNamedQuery("SRIArchivo.GetById", SRIArchivo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIArchivoUtil> GetAll() {
        
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

    @Override
    public Response descargarArchivo(int id) {
        
        try {
            SRIArchivo entidad = em.createNamedQuery("SRIArchivo.GetById", SRIArchivo.class).setParameter("idEntidad", id).getSingleResult();
            System.out.println("entidad   =====>  " + entidad);
            int blobLength;
            byte[] blobAsBytes;
            blobLength = (int) entidad.getBlobArchivo().length();
            blobAsBytes = entidad.getBlobArchivo().getBytes(1, blobLength);
            
            return Response
                    .ok(blobAsBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition",entidad.getSNombreArchivo())
                    .build();
        } catch (SQLException ex) {
            Logger.getLogger(ArchivoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
