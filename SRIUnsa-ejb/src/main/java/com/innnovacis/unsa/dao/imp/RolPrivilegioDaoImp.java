
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IRolPrivilegioDao;
import com.innnovacis.unsa.model.SRIRolPrivilegio;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class RolPrivilegioDaoImp implements IRolPrivilegioDao {

    @Inject
    private EntityManager em;

    @Override
    public SRIRolPrivilegio  Insert(SRIRolPrivilegio entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRIRolPrivilegio Update(SRIRolPrivilegio entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRIRolPrivilegio entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIRolPrivilegio GetById(int idEntidad) {
        SRIRolPrivilegio entidad = em.createNamedQuery("SRIRolPrivilegio.GetById", SRIRolPrivilegio.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIRolPrivilegio> GetAll() {
        List<SRIRolPrivilegio> olistaRespuesta = em.createNamedQuery("SRIRolPrivilegio.GetAll",SRIRolPrivilegio.class).getResultList();
        return olistaRespuesta;
    }

}
