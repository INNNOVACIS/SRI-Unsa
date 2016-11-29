
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class PlanificacionActividadDaoImp implements IPlanificacionActividadDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPlanificacionActividad  Insert(SRIPlanificacionActividad entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIPlanificacionActividad Update(SRIPlanificacionActividad entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIPlanificacionActividad entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIPlanificacionActividad GetById(int idEntidad) {
        SRIPlanificacionActividad entidad = em.createNamedQuery("SRIPlanificacionActividad.GetById", SRIPlanificacionActividad.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIPlanificacionActividad> GetAll() {
        List<SRIPlanificacionActividad> olistaRespuesta = em.createNamedQuery("SRIPlanificacionActividad.GetAll",SRIPlanificacionActividad.class).getResultList();
        return olistaRespuesta;
    }

}
