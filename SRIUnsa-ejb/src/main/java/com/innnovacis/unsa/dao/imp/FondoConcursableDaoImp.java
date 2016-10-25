
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFondoConcursableDao;
import com.innnovacis.unsa.model.SRIFondoConcursable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class FondoConcursableDaoImp implements IFondoConcursableDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIFondoConcursable  Insert(SRIFondoConcursable entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIFondoConcursable Update(SRIFondoConcursable entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIFondoConcursable entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIFondoConcursable GetById(int idEntidad) {
        SRIFondoConcursable entidad = em.createNamedQuery("SRIFondoConcursable.GetById", SRIFondoConcursable.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIFondoConcursable> GetAll() {
        List<SRIFondoConcursable> olistaRespuesta = em.createNamedQuery("SRIFondoConcursable.GetAll",SRIFondoConcursable.class).getResultList();
        return olistaRespuesta;
    }

}
