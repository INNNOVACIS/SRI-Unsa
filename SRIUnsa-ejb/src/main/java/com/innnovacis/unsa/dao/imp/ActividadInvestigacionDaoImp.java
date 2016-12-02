
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IActividadInvestigacionDao;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class ActividadInvestigacionDaoImp implements IActividadInvestigacionDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIActividadInvestigacion  Insert(SRIActividadInvestigacion entidad) {
        entidad.setDFechaCreacion(new Date());
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRIActividadInvestigacion Update(SRIActividadInvestigacion entidad) {
        entidad.setDFechaModificacion(new Date());
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIActividadInvestigacion entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIActividadInvestigacion GetById(int idEntidad) {
        SRIActividadInvestigacion entidad = em.createNamedQuery("SRIActividadInvestigacion.GetById", SRIActividadInvestigacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIActividadInvestigacion> GetAll() {
        List<SRIActividadInvestigacion> olistaRespuesta = em.createNamedQuery("SRIActividadInvestigacion.GetAll",SRIActividadInvestigacion.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetPagina(SRIPaginacion entidad, String idActividades) {
        Query query = em.createNativeQuery("{call GetActividadesPaginacion(?1,?2,?3,?4,?5,?6,?7,?8,?9)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage())
                        .setParameter(9, idActividades);
        List<SRIActividadGeneralPaginacion> listActividades = query.getResultList();
        return listActividades;
    }

    @Override
    public int GetTotalPagina(SRIPaginacion entidad, String idActividades) {
        Query query = em.createNativeQuery("{call GetActividadesTotalPaginacion(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, idActividades);
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public int GetTotalActividadesGeneradas(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetTotalActividadesGeneradas(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesGeneradas(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetActividadesGeneradas(?1,?2,?3,?4,?5,?6,?7,?8,?9)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage())
                        .setParameter(9, entidad.getIdUsuario());
        List<SRIActividadGeneralPaginacion> listActividades = query.getResultList();
        return listActividades;
    }

    
    @Override
    public int GetTotalActividadesPendientes(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetTotalActividadesPendientes(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }
    
    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesPendientes(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetActividadesPendientes(?1,?2,?3,?4,?5,?6,?7,?8,?9)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage())
                        .setParameter(9, entidad.getIdUsuario());
        List<SRIActividadGeneralPaginacion> listActividades = query.getResultList();
        return listActividades;
    }

    @Override
    public int GetTotalActividadesRevisadas(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetTotalActividadesRevisadas(?1,?2,?3,?4,?5,?6,?7,?8)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario())
                        .setParameter(8, entidad.getCodigoActor());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesRevisadas(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call GetActividadesRevisadas(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage())
                        .setParameter(9, entidad.getIdUsuario())
                        .setParameter(10, entidad.getCodigoActor());
        List<SRIActividadGeneralPaginacion> listActividades = query.getResultList();
        return listActividades;
    }
    
}
