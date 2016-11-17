
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoAsesoriaDao;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.model.SRITipoAsesoria;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class TipoAsesoriaDaoImp implements ITipoAsesoriaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoAsesoria  Insert(SRITipoAsesoria entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoAsesoria Update(SRITipoAsesoria entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoAsesoria entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoAsesoria GetById(int idEntidad) {
        SRITipoAsesoria entidad = em.createNamedQuery("SRITipoAsesoria.GetById", SRITipoAsesoria.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoAsesoria> GetAll() {
        List<SRITipoAsesoria> olistaRespuesta = em.createNamedQuery("SRITipoAsesoria.GetAll",SRITipoAsesoria.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
         Query query = em.createNativeQuery("{call tipoAsesoriaTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRITipoAsesoria> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoAsesoriaPaginacion(?1,?2,?3)}", SRITipoAsesoria.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRITipoAsesoria> listTipoAsesoria = query.getResultList();
        return listTipoAsesoria;
    }

}
