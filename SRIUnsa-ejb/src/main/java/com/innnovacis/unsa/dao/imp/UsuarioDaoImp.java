
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIUsuario;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.transaction.Transactional;



@Dependent
public class UsuarioDaoImp implements IUsuarioDao {

    @Inject
    private EntityManager em;
    
    
    @Override
    @Transactional
    public SRIUsuario  Insert(SRIUsuario entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIUsuario Update(SRIUsuario entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuario entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuario GetById(int idEntidad) {
        SRIUsuario entidad = em.createNamedQuery("SRIUsuario.GetById", SRIUsuario.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuario> GetAll() {
        List<SRIUsuario> olistaRespuesta = em.createNamedQuery("SRIUsuario.GetAll",SRIUsuario.class).getResultList();
        return olistaRespuesta;
    }

}
