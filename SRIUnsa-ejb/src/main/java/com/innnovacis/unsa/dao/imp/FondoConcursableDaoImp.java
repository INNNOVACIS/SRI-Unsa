
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFondoConcursableDao;
import com.innnovacis.unsa.model.SRIFondoConcursable;
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

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        Query query = em.createNativeQuery("{call GetTotalFondoConcursable(?1)}")
                        .setParameter(1, entidad.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIFondoConcursable> GetPagina(SRIPaginacionObject entidad) {
        Query query = em.createNativeQuery("{call GetFondoConcursable(?1,?2,?3)}", SRIFondoConcursable.class)
                        .setParameter(1, entidad.getFiltro())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
        List<SRIFondoConcursable> listFondos = query.getResultList();
        return listFondos;
    }

}
