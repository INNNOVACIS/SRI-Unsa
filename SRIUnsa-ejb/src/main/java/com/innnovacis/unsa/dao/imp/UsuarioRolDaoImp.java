
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioRolDao;
import com.innnovacis.unsa.model.SRIUsuarioRol;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class UsuarioRolDaoImp implements IUsuarioRolDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIUsuarioRol  Insert(SRIUsuarioRol entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIUsuarioRol Update(SRIUsuarioRol entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuarioRol entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuarioRol GetById(int idEntidad) {
        SRIUsuarioRol entidad = em.createNamedQuery("SRIUsuarioRol.GetById", SRIUsuarioRol.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuarioRol> GetAll() {
        List<SRIUsuarioRol> olistaRespuesta = em.createNamedQuery("SRIUsuarioRol.GetAll",SRIUsuarioRol.class).getResultList();
        return olistaRespuesta;
    }

}
