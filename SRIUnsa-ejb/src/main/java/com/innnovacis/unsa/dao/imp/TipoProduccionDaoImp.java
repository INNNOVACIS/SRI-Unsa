
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoProduccionDao;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.model.SRITipoProduccion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class TipoProduccionDaoImp implements ITipoProduccionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoProduccion  Insert(SRITipoProduccion entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoProduccion Update(SRITipoProduccion entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoProduccion entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoProduccion GetById(int idEntidad) {
        SRITipoProduccion entidad = em.createNamedQuery("SRITipoProduccion.GetById", SRITipoProduccion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoProduccion> GetAll() {
        List<SRITipoProduccion> olistaRespuesta = em.createNamedQuery("SRITipoProduccion.GetAll",SRITipoProduccion.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoProduccionTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRITipoProduccion> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoProduccionPaginacion(?1,?2,?3)}", SRITipoProduccion.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRITipoProduccion> listProduccion = query.getResultList();
        return listProduccion;
    }

}
