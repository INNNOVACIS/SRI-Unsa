
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoNivelDao;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.model.SRITipoNivel;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class TipoNivelDaoImp implements ITipoNivelDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoNivel  Insert(SRITipoNivel entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoNivel Update(SRITipoNivel entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoNivel entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoNivel GetById(int idEntidad) {
        SRITipoNivel entidad = em.createNamedQuery("SRITipoNivel.GetById", SRITipoNivel.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoNivel> GetAll() {
        List<SRITipoNivel> olistaRespuesta = em.createNamedQuery("SRITipoNivel.GetAll",SRITipoNivel.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoNivelTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRITipoNivel> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call tipoNivelPaginacion(?1,?2,?3)}", SRITipoNivel.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRITipoNivel> listNiveles = query.getResultList();
        return listNiveles;
    }

}
