
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFondoConcursableDao;
import com.innnovacis.unsa.model.SRIFondoConcursable;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class FondoConcursableDaoImp implements IFondoConcursableDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIFondoConcursable  Insert(SRIFondoConcursable entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIFondoConcursable Update(SRIFondoConcursable entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIFondoConcursable entidad) {
        entidad.setDFechaModificacion(new Date());
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
