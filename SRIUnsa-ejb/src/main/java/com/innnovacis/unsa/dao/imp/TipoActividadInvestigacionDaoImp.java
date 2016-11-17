
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoActividadInvestigacionDao;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
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

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoInvestigacionTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRITipoActividadInvestigacion> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoInvestigacionPaginacion(?1,?2,?3)}", SRITipoActividadInvestigacion.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRITipoActividadInvestigacion> listTipoInvestigacion = query.getResultList();
        return listTipoInvestigacion;
    }

}
