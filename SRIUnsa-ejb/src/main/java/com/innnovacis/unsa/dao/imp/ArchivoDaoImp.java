
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IArchivoDao;
import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@RequestScoped
public class ArchivoDaoImp implements IArchivoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIArchivo  Insert(SRIArchivo entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIArchivo Update(SRIArchivo entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIArchivo entidad) {
        entidad.setDFechaModificacion(new Date());
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

    @Override
    public List<SRIArchivoUtil> GetArchivosById(int id) {
        
        List<SRIArchivoUtil> lstArchivoUtil = new ArrayList<SRIArchivoUtil>();
        Query query = em.createNativeQuery("{call getArchivosByIdActividad(?1)}", SRIArchivo.class)
                        .setParameter(1, id);
        List<SRIArchivo> olistaRespuesta = query.getResultList();
        
        for(SRIArchivo sriArchivo : olistaRespuesta){
            SRIArchivoUtil objArchivoUtil = new SRIArchivoUtil();
            objArchivoUtil.setId(sriArchivo.getNIdArchivo());
            objArchivoUtil.setNombre(sriArchivo.getSNombreArchivo());
            lstArchivoUtil.add(objArchivoUtil);
        }
        return lstArchivoUtil;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call archivoTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIArchivoUtil> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call archivoPaginacion(?1,?2,?3)}", SRIArchivo.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIArchivo> olistaRespuesta = query.getResultList();
        List<SRIArchivoUtil> lstArchivoUtil = new ArrayList<SRIArchivoUtil>();
        
        for(SRIArchivo sriArchivo : olistaRespuesta){
            SRIArchivoUtil objArchivoUtil = new SRIArchivoUtil();
            objArchivoUtil.setId(sriArchivo.getNIdArchivo());
            objArchivoUtil.setNombre(sriArchivo.getSNombreArchivo());
            lstArchivoUtil.add(objArchivoUtil);
        }
        
        return lstArchivoUtil;
    }

}
