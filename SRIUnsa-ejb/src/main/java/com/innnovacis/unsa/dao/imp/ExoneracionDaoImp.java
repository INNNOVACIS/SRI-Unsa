
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IExoneracionDao;
import com.innnovacis.unsa.model.SRIExoneracion;
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
public class ExoneracionDaoImp implements IExoneracionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIExoneracion  Insert(SRIExoneracion entidad) {
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
    public SRIExoneracion Update(SRIExoneracion entidad) {
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
    public boolean Delete(SRIExoneracion entidad) {
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
    public SRIExoneracion GetById(int idEntidad) {
        SRIExoneracion entidad = null;
        try {
            entidad = em.createNamedQuery("SRIExoneracion.GetById", SRIExoneracion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex){
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIExoneracion> GetAll() {
        List<SRIExoneracion> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIExoneracion.GetAll",SRIExoneracion.class).getResultList();
        } catch(Exception ex){
            throw ex;
        }
        return olistaRespuesta;
    }
    
    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call exoneracionTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIExoneracion> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call exoneracionPaginacion(?1,?2,?3)}", SRIExoneracion.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIExoneracion> listObject = query.getResultList();
        return listObject;
    }

}
