
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IRolPrivilegioDao;
import com.innnovacis.unsa.model.SRIRolPrivilegio;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class RolPrivilegioDaoImp implements IRolPrivilegioDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIRolPrivilegio  Insert(SRIRolPrivilegio entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIRolPrivilegio Update(SRIRolPrivilegio entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIRolPrivilegio entidad) {
        entidad.setDFechaModificacion(new Date());
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
