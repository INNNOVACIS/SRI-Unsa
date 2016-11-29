
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class ProcesoFlujoDaoImp implements IProcesoFlujoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIProcesoFlujo  Insert(SRIProcesoFlujo entidad) {
        entidad.setDFechaCreacion(new Date());
        Query query = em.createNativeQuery("{call getProcesoFlujo(?1,?2)}", SRIProcesoFlujo.class)
                        .setParameter(1, entidad.getNIdArista())
                        .setParameter(2, entidad.getNIdUsuarioFlujo());
        List<SRIProcesoFlujo> listProcesoFlujo = query.getResultList();
        if(listProcesoFlujo.isEmpty()){
            em.persist(entidad);
        } else {
            entidad = listProcesoFlujo.get(0);
        }
        return entidad;
    }

    @Override
    @Transactional
    public SRIProcesoFlujo Update(SRIProcesoFlujo entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIProcesoFlujo entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIProcesoFlujo GetById(int idEntidad) {
        SRIProcesoFlujo entidad = em.createNamedQuery("SRIProcesoFlujo.GetById", SRIProcesoFlujo.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIProcesoFlujo> GetAll() {
        List<SRIProcesoFlujo> olistaRespuesta = em.createNamedQuery("SRIProcesoFlujo.GetAll",SRIProcesoFlujo.class).getResultList();
        return olistaRespuesta;
    }

}
