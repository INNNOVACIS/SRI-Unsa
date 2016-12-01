
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;

import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class DetalleInvestigacionFlujoDaoImp implements IDetalleInvestigacionFlujoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIDetalleInvestigacionFlujo  Insert(SRIDetalleInvestigacionFlujo entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIDetalleInvestigacionFlujo Update(SRIDetalleInvestigacionFlujo entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIDetalleInvestigacionFlujo entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIDetalleInvestigacionFlujo GetById(int idEntidad) {
        SRIDetalleInvestigacionFlujo entidad = em.createNamedQuery("SRIDetalleInvestigacionFlujo.GetById", SRIDetalleInvestigacionFlujo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIDetalleInvestigacionFlujo> GetAll() {
        List<SRIDetalleInvestigacionFlujo> olistaRespuesta = em.createNamedQuery("SRIDetalleInvestigacionFlujo.GetAll",SRIDetalleInvestigacionFlujo.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public List<SRIDetalleInvestigacionFlujo> GetByIdProcesoFlujo(String stringIdProcesoFlujo) {
        Query query = em.createNativeQuery("{call GetDetalleInvestigacionFlujoByIdProcesoFlujo(?1)}", SRIDetalleInvestigacionFlujo.class)
                        .setParameter(1, stringIdProcesoFlujo);
        List<SRIDetalleInvestigacionFlujo> listDetalleInvestigacionFlujo = query.getResultList();
        return listDetalleInvestigacionFlujo;
    }

    @Override
    public SRIDetalleInvestigacionFlujo GetByIdActividad(int idActividad) {
        SRIDetalleInvestigacionFlujo entidad = em.createNamedQuery("SRIDetalleInvestigacionFlujo.GetByIdActividad", SRIDetalleInvestigacionFlujo.class).setParameter("idEntidad", idActividad).getSingleResult();
        return entidad;
    }

}
