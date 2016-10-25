
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class PlanificacionActividadDaoImp implements IPlanificacionActividadDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIPlanificacionActividad  Insert(SRIPlanificacionActividad entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIPlanificacionActividad Update(SRIPlanificacionActividad entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIPlanificacionActividad entidad) {
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
