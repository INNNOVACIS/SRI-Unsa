
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ISemestreDao;
import com.innnovacis.unsa.model.SRISemestre;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;



@RequestScoped
public class SemestreDaoImp implements ISemestreDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRISemestre  Insert(SRISemestre entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRISemestre Update(SRISemestre entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRISemestre entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRISemestre GetById(int idEntidad) {
        SRISemestre entidad = em.createNamedQuery("SRISemestre.GetById", SRISemestre.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRISemestre> GetAll() {
        List<SRISemestre> olistaRespuesta = em.createNamedQuery("SRISemestre.GetAll",SRISemestre.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call semestreTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRISemestre> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call semestrePaginacion(?1,?2,?3)}", SRISemestre.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRISemestre> listSemestre = query.getResultList();
        return listSemestre;
    }

}
