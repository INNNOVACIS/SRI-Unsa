
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPersonaDao;
import com.innnovacis.unsa.model.SRIPersona;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class PersonaDaoImp implements IPersonaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPersona Insert(SRIPersona entidad) {
        try {
            entidad.setDFechaCreacion(new Date());
            em.persist(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public SRIPersona Update(SRIPersona entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public boolean Delete(SRIPersona entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return true;
    }

    @Override
    public SRIPersona GetById(int idEntidad) {
        SRIPersona entidad = null;
        try{
            entidad = em.createNamedQuery("SRIPersona.GetById", SRIPersona.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIPersona> GetAll() {
        List<SRIPersona> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIPersona.GetAll",SRIPersona.class).getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return olistaRespuesta;
    }


}
