
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.SRIFlujoActorUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioFlujoUtil;
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
public class UsuarioFlujoImp implements IUsuarioFlujoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIUsuarioFlujo  Insert(SRIUsuarioFlujo entidad) {
        entidad.setDFechaCreacion(new Date());
        entidad.setDFechaModificacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIUsuarioFlujo Update(SRIUsuarioFlujo entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuarioFlujo entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuarioFlujo GetById(int idEntidad) {
        SRIUsuarioFlujo entidad = em.createNamedQuery("SRIUsuarioFlujo.GetById", SRIUsuarioFlujo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuarioFlujo> GetAll() {
        List<SRIUsuarioFlujo> olistaRespuesta = em.createNamedQuery("SRIUsuarioFlujo.GetAll",SRIUsuarioFlujo.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call getUsuarioFlujoTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIUsuarioFlujoUtil> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call getUsuarioFlujoPaginacion(?1,?2,?3)}", SRIUsuarioFlujoUtil.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIUsuarioFlujoUtil> listUsuarioFlujo = query.getResultList();
        return listUsuarioFlujo;
    }

    @Override
        public List<SRIUsuarioFlujo> getUsuarioFlujoByIdUsuario(int id) {
        List<SRIUsuarioFlujo> entidad = em.createNamedQuery("SRIUsuarioFlujo.GetByIdUsuario", SRIUsuarioFlujo.class).setParameter("idEntidad", id).getResultList();
        return entidad;
    }
    
    @Override
    public List<SRIFlujoActorUtil> getUsuarioFlujoActorByIdUsuario(int id) {
        Query query = em.createNativeQuery("{call getUsuarioActorByidUsuario(?1)}", SRIFlujoActorUtil.class)
                        .setParameter(1, id);
        List<SRIFlujoActorUtil> listFlujoActor = query.getResultList();
        return listFlujoActor;
    }

    @Override
    @Transactional
    public int CreateAndGetUsuarioFlujo(SRIUsuarioFlujo entidad) {
        entidad.setDFechaCreacion(new Date());
        Query query = em.createNativeQuery("{call CreateAndGetUsuarioFlujo(?1,?2)}", SRIUsuarioFlujo.class)
                        .setParameter(1, entidad.getNIdFlujoActor())
                        .setParameter(2, entidad.getNIdUsuario());
        List<SRIUsuarioFlujo> usuarioFlujo = query.getResultList();
        if(usuarioFlujo.isEmpty()) {
            em.persist(entidad);
        } else {
            entidad = usuarioFlujo.get(0);
        }
        return entidad.getNIdUsuarioFlujo();
    }
}
