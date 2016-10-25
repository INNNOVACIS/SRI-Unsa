
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoNivelDao;
import com.innnovacis.unsa.model.SRITipoNivel;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class TipoNivelDaoImp implements ITipoNivelDao {

    @Inject
    private EntityManager em;

    @Override
    public SRITipoNivel  Insert(SRITipoNivel entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRITipoNivel Update(SRITipoNivel entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRITipoNivel entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoNivel GetById(int idEntidad) {
        SRITipoNivel entidad = em.createNamedQuery("SRITipoNivel.GetById", SRITipoNivel.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoNivel> GetAll() {
        List<SRITipoNivel> olistaRespuesta = em.createNamedQuery("SRITipoNivel.GetAll",SRITipoNivel.class).getResultList();
        return olistaRespuesta;
    }

}
