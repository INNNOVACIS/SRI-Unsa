
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IRolDao;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class RolDaoImp implements IRolDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIRol  Insert(SRIRol entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIRol Update(SRIRol entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIRol entidad) {
        entidad.setDFechaModificacion(new Date());
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

    @Override
    public List<SRIRol> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call rolPaginacion(?1,?2,?3)}", SRIRol.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIRol> listRoles = query.getResultList();
        return listRoles;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call rolTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

}
