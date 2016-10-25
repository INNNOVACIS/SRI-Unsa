
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoActividadInvestigacionDao;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class TipoActividadInvestigacionDaoImp implements ITipoActividadInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    public SRITipoActividadInvestigacion  Insert(SRITipoActividadInvestigacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRITipoActividadInvestigacion Update(SRITipoActividadInvestigacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
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
