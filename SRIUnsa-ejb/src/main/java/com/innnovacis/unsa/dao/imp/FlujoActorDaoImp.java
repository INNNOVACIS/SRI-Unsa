
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFlujoActorDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
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
public class FlujoActorDaoImp implements IFlujoActorDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIFlujoActor  Insert(SRIFlujoActor entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIFlujoActor Update(SRIFlujoActor entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIFlujoActor entidad) {
        entidad.setDFechaModificacion(new Date());
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

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call flujoActorTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIFlujoActor> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call flujoActorPaginacion(?1,?2,?3)}", SRIFlujoActor.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIFlujoActor> listFlujoActor = query.getResultList();
        return listFlujoActor;
    }

}
