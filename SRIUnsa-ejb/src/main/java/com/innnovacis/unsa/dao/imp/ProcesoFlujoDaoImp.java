
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class ProcesoFlujoDaoImp implements IProcesoFlujoDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIProcesoFlujo  Insert(SRIProcesoFlujo entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIProcesoFlujo Update(SRIProcesoFlujo entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIProcesoFlujo entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIProcesoFlujo GetById(int idEntidad) {
        SRIProcesoFlujo entidad = em.createNamedQuery("SRIProcesoFlujo.GetById", SRIProcesoFlujo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIProcesoFlujo> GetAll() {
        List<SRIProcesoFlujo> olistaRespuesta = em.createNamedQuery("SRIProcesoFlujo.GetAll",SRIProcesoFlujo.class).getResultList();
        return olistaRespuesta;
    }

}
