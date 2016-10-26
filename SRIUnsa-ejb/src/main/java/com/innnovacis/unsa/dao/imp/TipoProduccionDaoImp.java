
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoProduccionDao;
import com.innnovacis.unsa.model.SRITipoProduccion;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class TipoProduccionDaoImp implements ITipoProduccionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoProduccion  Insert(SRITipoProduccion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoProduccion Update(SRITipoProduccion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoProduccion entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoProduccion GetById(int idEntidad) {
        SRITipoProduccion entidad = em.createNamedQuery("SRITipoProduccion.GetById", SRITipoProduccion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoProduccion> GetAll() {
        List<SRITipoProduccion> olistaRespuesta = em.createNamedQuery("SRITipoProduccion.GetAll",SRITipoProduccion.class).getResultList();
        return olistaRespuesta;
    }

}
