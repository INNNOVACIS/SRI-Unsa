
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
        try {
            entidad.setDFechaCreacion(new Date());
            em.persist(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public SRIActividadInvestigacion Update(SRIActividadInvestigacion entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIActividadInvestigacion entidad) {
        try {
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return true;
    }

    @Override
    public SRIActividadInvestigacion GetById(int idEntidad) {
        SRIActividadInvestigacion entidad = null;
        try {
            entidad = em.createNamedQuery("SRIActividadInvestigacion.GetById", SRIActividadInvestigacion.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIActividadInvestigacion> GetAll() {
        List<SRIActividadInvestigacion> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIActividadInvestigacion.GetAll",SRIActividadInvestigacion.class).getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return olistaRespuesta;
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetPagina(SRIPaginacion entidad, String idActividades) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
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
        listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    @Override
    public int GetTotalPagina(SRIPaginacion entidad, String idActividades) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetActividadesTotalPaginacion(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, idActividades);
        total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public int GetTotalActividadesGeneradas(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesGeneradas(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesGeneradas(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
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
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    
    @Override
    public int GetTotalActividadesPendientes(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesPendientes(?1,?2,?3,?4,?5,?6,?7)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }
    
    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesPendientes(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
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
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    @Override
    public int GetTotalActividadesRevisadas(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesRevisadas(?1,?2,?3,?4,?5,?6,?7,?8)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getIdUsuario())
                        .setParameter(8, entidad.getCodigoActor());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesRevisadas(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
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
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }
    
    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesRevisadasMasivas(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
             Query query = em.createNativeQuery("{call GetActividadesRevisadasMasivas(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)}", SRIActividadGeneralPaginacion.class)
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
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    @Override
    public int GetTotalActividadesByDocente(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesByDocente(?1,?2,?3,?4,?5,?6)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesByDocente(SRIPaginacion entidad) {
        
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
             Query query = em.createNativeQuery("{call GetActividadesByDocente(?1,?2,?3,?4,?5,?6,?7,?8)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage());
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }
    
    @Override
    public int GetTotalActividadesByDocenteColaboradores(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesByDocenteColaboradores(?1,?2,?3,?4,?5,?6)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetActividadesByDocenteColaboradores(SRIPaginacion entidad) {
        List<SRIActividadGeneralPaginacion> listActividades = null;
        try {
             Query query = em.createNativeQuery("{call GetActividadesByDocenteColaboradores(?1,?2,?3,?4,?5,?6,?7,?8)}", SRIActividadGeneralPaginacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage());
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }

    @Override
    public int GetTotalActividadesByDocenteDetalle(SRIPaginacion entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalActividadesDocenteDetalle(?1,?2,?3,?4,?5,?6)}")
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIActividadInvestigacion> GetActividadesByDocenteDetalle(SRIPaginacion entidad) {
        List<SRIActividadInvestigacion> listActividades = null;
        try {
             Query query = em.createNativeQuery("{call GetActividadesDocenteDetalle(?1,?2,?3,?4,?5,?6,?7,?8)}", SRIActividadInvestigacion.class)
                        .setParameter(1, entidad.getFiltro().getNIdTipoActividadInvestigacion())
                        .setParameter(2, entidad.getFiltro().getSFacultad())
                        .setParameter(3, entidad.getFiltro().getSDepartamento())
                        .setParameter(4, entidad.getFiltro().getSEscuela())
                        .setParameter(5, entidad.getFiltro().getSSemestre())
                        .setParameter(6, entidad.getFiltro().getSFondoConcursable())
                        .setParameter(7, entidad.getRango())
                        .setParameter(8, entidad.getCurrentPage());
            listActividades = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listActividades;
    }
}
