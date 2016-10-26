
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoActividadInvestigacionDao;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class TipoActividadInvestigacionDaoImp implements ITipoActividadInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoActividadInvestigacion  Insert(SRITipoActividadInvestigacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoActividadInvestigacion Update(SRITipoActividadInvestigacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoActividadInvestigacion entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoActividadInvestigacion GetById(int idEntidad) {
        SRITipoActividadInvestigacion entidad = em.createNamedQuery("SRITipoActividadInvestigacion.GetById", SRITipoActividadInvestigacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoActividadInvestigacion> GetAll() {
        List<SRITipoActividadInvestigacion> olistaRespuesta = em.createNamedQuery("SRITipoActividadInvestigacion.GetAll",SRITipoActividadInvestigacion.class).getResultList();
        return olistaRespuesta;
    }

}
