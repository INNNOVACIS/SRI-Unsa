
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstadoDao;
import com.innnovacis.unsa.model.SRIEstado;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class EstadoDaoImp implements IEstadoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIEstado  Insert(SRIEstado entidad) {
        try {
            entidad.setDFechaCreacion(new Date());
            em.persist(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public SRIEstado Update(SRIEstado entidad) {
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
    public boolean Delete(SRIEstado entidad) {
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
    public SRIEstado GetById(int idEntidad) {
        SRIEstado entidad = null;
        try {
            entidad = em.createNamedQuery("SRIEstado.GetById", SRIEstado.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIEstado> GetAll() {
        List<SRIEstado> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIEstado.GetAll",SRIEstado.class).getResultList();
        } catch(Exception ex){
            throw ex;
        }
        return olistaRespuesta;
    }

}
