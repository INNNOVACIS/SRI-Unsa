
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstructuraAreaInvestigacionDao;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class EstructuraAreaInvestigacionDaoImp implements IEstructuraAreaInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIEstructuraAreaInvestigacion  Insert(SRIEstructuraAreaInvestigacion entidad) {
        entidad.setDFechaCreacion(new Date());
        entidad.setDFechaModificacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIEstructuraAreaInvestigacion Update(SRIEstructuraAreaInvestigacion entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIEstructuraAreaInvestigacion entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIEstructuraAreaInvestigacion GetById(int idEntidad) {
        SRIEstructuraAreaInvestigacion entidad = em.createNamedQuery("SRIEstructuraAreaInvestigacion.GetById", SRIEstructuraAreaInvestigacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIEstructuraAreaInvestigacion> GetAll() {
        List<SRIEstructuraAreaInvestigacion> olistaRespuesta = em.createNamedQuery("SRIEstructuraAreaInvestigacion.GetAll",SRIEstructuraAreaInvestigacion.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call areaInvestigacionTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIEstructuraAreaInvestigacion> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call areaInvestigacionPaginacion(?1,?2,?3)}", SRIEstructuraAreaInvestigacion.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIEstructuraAreaInvestigacion> listAreaInvestigacion = query.getResultList();
        return listAreaInvestigacion;
    }

}
