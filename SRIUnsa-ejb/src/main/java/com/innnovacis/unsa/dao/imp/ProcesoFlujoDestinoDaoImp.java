
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IProcesoFlujoDestinoDao;
import com.innnovacis.unsa.model.SRIProcesoFlujoDestino;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class ProcesoFlujoDestinoDaoImp implements IProcesoFlujoDestinoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIProcesoFlujoDestino  Insert(SRIProcesoFlujoDestino entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIProcesoFlujoDestino Update(SRIProcesoFlujoDestino entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIProcesoFlujoDestino entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIProcesoFlujoDestino GetById(int idEntidad) {
        SRIProcesoFlujoDestino entidad = em.createNamedQuery("SRIProcesoFlujoDestino.GetById", SRIProcesoFlujoDestino.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIProcesoFlujoDestino> GetAll() {
        List<SRIProcesoFlujoDestino> olistaRespuesta = em.createNamedQuery("SRIProcesoFlujoDestino.GetAll",SRIProcesoFlujoDestino.class).getResultList();
        return olistaRespuesta;
    }

}
