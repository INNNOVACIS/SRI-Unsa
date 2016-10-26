
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IRolDao;
import com.innnovacis.unsa.model.SRIRol;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class RolDaoImp implements IRolDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIRol  Insert(SRIRol entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIRol Update(SRIRol entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIRol entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIRol GetById(int idEntidad) {
        SRIRol entidad = em.createNamedQuery("SRIRol.GetById", SRIRol.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIRol> GetAll() {
        List<SRIRol> olistaRespuesta = em.createNamedQuery("SRIRol.GetAll",SRIRol.class).getResultList();
        return olistaRespuesta;
    }

}
