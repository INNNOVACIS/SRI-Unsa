
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPrivilegioDao;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class PrivilegioDaoImp implements IPrivilegioDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPrivilegio  Insert(SRIPrivilegio entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIPrivilegio Update(SRIPrivilegio entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
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

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call privilegioTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIPrivilegio> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call privilegioPaginacion(?1,?2,?3)}", SRIPrivilegio.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIPrivilegio> listPrivilegios = query.getResultList();
        return listPrivilegios;
    }

}
