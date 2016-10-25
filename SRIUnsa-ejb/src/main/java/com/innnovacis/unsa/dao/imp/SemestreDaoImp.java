
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ISemestreDao;
import com.innnovacis.unsa.model.SRISemestre;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class SemestreDaoImp implements ISemestreDao {

    @Inject
    private EntityManager em;

    @Override
    public SRISemestre  Insert(SRISemestre entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    public SRISemestre Update(SRISemestre entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    public boolean Delete(SRISemestre entidad) {
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

}
