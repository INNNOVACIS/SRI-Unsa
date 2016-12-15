
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IDetalleMasivaDao;
import com.innnovacis.unsa.model.SRIDetalleMasiva;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;


@Dependent
public class DetalleMasivaDaoImp implements IDetalleMasivaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIDetalleMasiva  Insert(SRIDetalleMasiva entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIDetalleMasiva Update(SRIDetalleMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIDetalleMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIDetalleMasiva GetById(int idEntidad) {
        SRIDetalleMasiva entidad = em.createNamedQuery("SRIDetalleMasiva.GetById", SRIDetalleMasiva.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

}
