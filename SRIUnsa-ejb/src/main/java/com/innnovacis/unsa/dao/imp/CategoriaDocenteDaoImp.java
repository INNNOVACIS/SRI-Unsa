
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ICategoriaDocenteDao;
import com.innnovacis.unsa.model.SRICategoriaDocente;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;



@RequestScoped
public class CategoriaDocenteDaoImp implements ICategoriaDocenteDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRICategoriaDocente  Insert(SRICategoriaDocente entidad) {
        try {
            entidad.setDFechaCreacion(new Date());
            entidad.setDFechaModificacion(new Date());
            em.persist(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public SRICategoriaDocente Update(SRICategoriaDocente entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRICategoriaDocente entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex){
            throw ex;
        }
        return true;
    }

    @Override
    public SRICategoriaDocente GetById(int idEntidad) {
        SRICategoriaDocente entidad = null;
        try {
            entidad = em.createNamedQuery("SRICategoriaDocente.GetById", SRICategoriaDocente.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRICategoriaDocente> GetAll() {
        List<SRICategoriaDocente> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRICategoriaDocente.GetAll",SRICategoriaDocente.class).getResultList();
        } catch(Exception ex){
            throw ex;
        }
        return olistaRespuesta;
    }
    
    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call categoriaDocenteTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRICategoriaDocente> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call categoriaDocentePaginacion(?1,?2,?3)}", SRICategoriaDocente.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRICategoriaDocente> listNiveles = query.getResultList();
        return listNiveles;
    }

}
