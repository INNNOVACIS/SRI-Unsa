
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ICabeceraMasivaDao;
import com.innnovacis.unsa.model.SRICabeceraMasiva;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Dependent
public class CabeceraMasivaDaoImp implements ICabeceraMasivaDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRICabeceraMasiva  Insert(SRICabeceraMasiva entidad) {
        entidad.setDFechaCreacion(new Date());
        entidad.setDFechaModificacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRICabeceraMasiva Update(SRICabeceraMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRICabeceraMasiva entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRICabeceraMasiva GetById(int idEntidad) {
        SRICabeceraMasiva entidad = em.createNamedQuery("SRICabeceraMasiva.GetById", SRICabeceraMasiva.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRICabeceraDetalleMasiva> GetCabeceraMasiva(int idUsuarioFlujo) {
        List<SRICabeceraDetalleMasiva> listCabecera = null;
        try {
            Query query = em.createNativeQuery("{call GetCabeceraMasiva(?1)}", SRICabeceraDetalleMasiva.class)
                .setParameter(1, idUsuarioFlujo);
            listCabecera = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listCabecera;
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetDetalleMasiva(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
             Query query = em.createNativeQuery("{call GetDetalleMasiva(?1,?2,?3)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion()) //mandamos el idCabecera
                        .setParameter(2 , entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    @Override
    public int GetTotalDetalleMasiva(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
             Query query = em.createNativeQuery("{call GetTTotalDetalleMasiva(?1)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion()); //mandamos el idCabeceraDetalle
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

}
