
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.model.SRIFlujoArista;
import com.innnovacis.unsa.util.SRIFlujoAristaActorUtil;
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
public class FlujoAristaDaoImp implements IFlujoAristaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIFlujoArista  Insert(SRIFlujoArista entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIFlujoArista Update(SRIFlujoArista entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIFlujoArista entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIFlujoArista GetById(int idEntidad) {
        SRIFlujoArista entidad = em.createNamedQuery("SRIFlujoArista.GetById", SRIFlujoArista.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIFlujoArista> GetAll() {
        List<SRIFlujoArista> olistaRespuesta = em.createNamedQuery("SRIFlujoArista.GetAll",SRIFlujoArista.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call getFlujoAristaTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIFlujoAristaActorUtil> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call getFlujoAristaPaginacion(?1,?2,?3)}", SRIFlujoAristaActorUtil.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIFlujoAristaActorUtil> listFlujoActorArista = query.getResultList();
        return listFlujoActorArista;
    }

    @Override
    public SRIFlujoArista GetFlujoAristaByIdOrigenIdEstado(int idOrigen, int idEstado) {
        Query query = em.createNativeQuery("{call getFlujoAristaByIdOrigenIdEstado(?1,?2)}", SRIFlujoArista.class)
                        .setParameter(1, idOrigen)
                        .setParameter(2, idEstado);
        SRIFlujoArista objectFlujoArista = (SRIFlujoArista)query.getSingleResult();
        return objectFlujoArista;
    }

    @Override
    public SRIFlujoArista GetFlujoAristaByIdActorOrigen(int idOrigen) {
        Query query = em.createNativeQuery("{call GetFlujoAristaByIdActorOrigen(?1)}", SRIFlujoArista.class)
                        .setParameter(1, idOrigen);
        SRIFlujoArista objectFlujoArista = (SRIFlujoArista)query.getSingleResult();
        return objectFlujoArista;
    }
}
