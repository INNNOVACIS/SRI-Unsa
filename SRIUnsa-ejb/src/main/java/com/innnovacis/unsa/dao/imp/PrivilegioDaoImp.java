
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPrivilegioDao;
import com.innnovacis.unsa.model.SRIPrivilegio;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class PrivilegioDaoImp implements IPrivilegioDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIPrivilegio  Insert(SRIPrivilegio entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIPrivilegio Update(SRIPrivilegio entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIPrivilegio entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIPrivilegio GetById(int idEntidad) {
        SRIPrivilegio entidad = em.createNamedQuery("SRIPrivilegio.GetById", SRIPrivilegio.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIPrivilegio> GetAll() {
        List<SRIPrivilegio> olistaRespuesta = em.createNamedQuery("SRIPrivilegio.GetAll",SRIPrivilegio.class).getResultList();
        return olistaRespuesta;
    }

}
