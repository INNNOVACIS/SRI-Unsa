
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IEstadoDao;
import com.innnovacis.unsa.model.SRIEstado;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class EstadoDaoImp implements IEstadoDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIEstado  Insert(SRIEstado entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIEstado Update(SRIEstado entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIEstado entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIEstado GetById(int idEntidad) {
        SRIEstado entidad = em.createNamedQuery("SRIEstado.GetById", SRIEstado.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIEstado> GetAll() {
        List<SRIEstado> olistaRespuesta = em.createNamedQuery("SRIEstado.GetAll",SRIEstado.class).getResultList();
        return olistaRespuesta;
    }

}
