
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFlujoActorDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class FlujoActorDaoImp implements IFlujoActorDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIFlujoActor  Insert(SRIFlujoActor entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIFlujoActor Update(SRIFlujoActor entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIFlujoActor entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIFlujoActor GetById(int idEntidad) {
        SRIFlujoActor entidad = em.createNamedQuery("SRIFlujoActor.GetById", SRIFlujoActor.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIFlujoActor> GetAll() {
        List<SRIFlujoActor> olistaRespuesta = em.createNamedQuery("SRIFlujoActor.GetAll",SRIFlujoActor.class).getResultList();
        return olistaRespuesta;
    }

}
