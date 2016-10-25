
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.model.SRIFlujoArista;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class FlujoAristaDaoImp implements IFlujoAristaDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIFlujoArista  Insert(SRIFlujoArista entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIFlujoArista Update(SRIFlujoArista entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIFlujoArista entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIFlujoArista GetById(int idEntidad) {
        SRIFlujoArista entidad = em.createNamedQuery("SRIFlujoArista.GetById", SRIFlujoArista.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIFlujoArista> GetAll() {
        List<SRIFlujoArista> olistaRespuesta = em.createNamedQuery("SRIFlujoArista.GetAll",SRIFlujoArista.class).getResultList();
        return olistaRespuesta;
    }

}
