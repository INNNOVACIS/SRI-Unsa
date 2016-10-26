
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstructuraAreaInvestigacionDao;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class EstructuraAreaInvestigacionDaoImp implements IEstructuraAreaInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIEstructuraAreaInvestigacion  Insert(SRIEstructuraAreaInvestigacion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIEstructuraAreaInvestigacion Update(SRIEstructuraAreaInvestigacion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIEstructuraAreaInvestigacion entidad) {
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

}
