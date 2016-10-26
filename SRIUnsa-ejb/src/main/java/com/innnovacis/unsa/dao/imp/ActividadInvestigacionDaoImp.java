
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IActividadInvestigacionDao;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class ActividadInvestigacionDaoImp implements IActividadInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIActividadInvestigacion  Insert(SRIActividadInvestigacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIActividadInvestigacion Update(SRIActividadInvestigacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIActividadInvestigacion entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIActividadInvestigacion GetById(int idEntidad) {
        SRIActividadInvestigacion entidad = em.createNamedQuery("SRIActividadInvestigacion.GetById", SRIActividadInvestigacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIActividadInvestigacion> GetAll() {
        List<SRIActividadInvestigacion> olistaRespuesta = em.createNamedQuery("SRIActividadInvestigacion.GetAll",SRIActividadInvestigacion.class).getResultList();
        return olistaRespuesta;
    }

}
