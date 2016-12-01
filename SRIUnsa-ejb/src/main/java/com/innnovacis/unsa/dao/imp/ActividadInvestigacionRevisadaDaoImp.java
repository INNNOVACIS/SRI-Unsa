
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IActividadInvestigacionRevisadaDao;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.math.BigInteger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;


@Dependent
public class ActividadInvestigacionRevisadaDaoImp implements IActividadInvestigacionRevisadaDao {

    @Inject
    private EntityManager em;

    @Override
    public List<SRIActividadGeneralPaginacion> GetAll() {
        
        Query query = em.createNativeQuery("{call actividadesRevisadas()}", SRIActividadGeneralPaginacion.class);
        List<SRIActividadGeneralPaginacion> listActividades = (List<SRIActividadGeneralPaginacion>)query.getResultList();
        return listActividades;
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetPagina(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call actividades_paginacion(?1,?2,?3,?4,?5,?6,?7,?8)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage());
        List<SRIActividadGeneralPaginacion> listActividades = query.getResultList();
        return listActividades;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacion entidad) {
        Query query = em.createNativeQuery("{call total_paginacion(?1,?2,?3,?4,?5,?6)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

}
