
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstructuraOrganizacionDao;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class EstructuraOrganizacionDaoImp implements IEstructuraOrganizacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIEstructuraOrganizacion  Insert(SRIEstructuraOrganizacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIEstructuraOrganizacion Update(SRIEstructuraOrganizacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIEstructuraOrganizacion entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIEstructuraOrganizacion GetById(int idEntidad) {
        SRIEstructuraOrganizacion entidad = em.createNamedQuery("SRIEstructuraOrganizacion.GetById", SRIEstructuraOrganizacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIEstructuraOrganizacion> GetAll() {
        List<SRIEstructuraOrganizacion> olistaRespuesta = em.createNamedQuery("SRIEstructuraOrganizacion.GetAll",SRIEstructuraOrganizacion.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call estructuraOrganizacionTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIEstructuraOrganizacion> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call estructuraOrganizacionPaginacion(?1,?2,?3)}", SRIEstructuraOrganizacion.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIEstructuraOrganizacion> listEstructuraOrganizacion = query.getResultList();
        return listEstructuraOrganizacion;
    }

}
