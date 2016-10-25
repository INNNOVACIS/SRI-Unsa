
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class UsuarioFlujoImp implements IUsuarioFlujoDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIUsuarioFlujo  Insert(SRIUsuarioFlujo entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIUsuarioFlujo Update(SRIUsuarioFlujo entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIUsuarioFlujo entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuarioFlujo GetById(int idEntidad) {
        SRIUsuarioFlujo entidad = em.createNamedQuery("SRIUsuarioFlujo.GetById", SRIUsuarioFlujo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuarioFlujo> GetAll() {
        List<SRIUsuarioFlujo> olistaRespuesta = em.createNamedQuery("SRIUsuarioFlujo.GetAll",SRIUsuarioFlujo.class).getResultList();
        return olistaRespuesta;
    }

}
