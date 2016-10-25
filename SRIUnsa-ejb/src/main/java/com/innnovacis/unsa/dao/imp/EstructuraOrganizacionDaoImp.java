
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstructuraOrganizacionDao;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class EstructuraOrganizacionDaoImp implements IEstructuraOrganizacionDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIEstructuraOrganizacion  Insert(SRIEstructuraOrganizacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIEstructuraOrganizacion Update(SRIEstructuraOrganizacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
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

}
