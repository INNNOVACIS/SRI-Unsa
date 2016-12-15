
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ICabeceraMasivaDao;
import com.innnovacis.unsa.model.SRICabeceraMasiva;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;


@Dependent
public class CabeceraMasivaDaoImp implements ICabeceraMasivaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRICabeceraMasiva  Insert(SRICabeceraMasiva entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRICabeceraMasiva Update(SRICabeceraMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRICabeceraMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRICabeceraMasiva GetById(int idEntidad) {
        SRICabeceraMasiva entidad = em.createNamedQuery("SRICabeceraMasiva.GetById", SRICabeceraMasiva.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

}
