
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioExoneracionDao;
import com.innnovacis.unsa.model.SRIUsuarioExoneracion;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;



@RequestScoped
public class UsuarioExoneracionDaoImp implements IUsuarioExoneracionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIUsuarioExoneracion  Insert(SRIUsuarioExoneracion entidad) {
        try {
            entidad.setDFechaCreacion(new Date());
            entidad.setDFechaModificacion(new Date());
            em.persist(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public SRIUsuarioExoneracion Update(SRIUsuarioExoneracion entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuarioExoneracion entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return true;
    }

    @Override
    public SRIUsuarioExoneracion GetById(int idEntidad) {
        SRIUsuarioExoneracion entidad = null;
        try {
            entidad = em.createNamedQuery("SRIUsuarioExoneracion.GetById", SRIUsuarioExoneracion.class)
                        .setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIUsuarioExoneracion> GetAll() {
        List<SRIUsuarioExoneracion> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIUsuarioExoneracion.GetAll",SRIUsuarioExoneracion.class).getResultList();
        } catch(Exception ex){
            throw ex;
        }
        return olistaRespuesta;
    }

    @Override
    public SRIUsuarioExoneracion GetByIdUsuarioIdSemestre(int idUsuario, int idSemestre) {
        SRIUsuarioExoneracion entidad = null;
        try{
            Query query = em.createNativeQuery("{call GetUsuarioExoneracionByIdUsuarioIdSemestre(?1, ?2)}", SRIUsuarioExoneracion.class)
                    .setParameter(1, idUsuario)
                    .setParameter(2, idSemestre);
            entidad = (SRIUsuarioExoneracion)query.getSingleResult();
        } catch(Exception ex) {
            
        }
        return entidad;
    }
    
}
