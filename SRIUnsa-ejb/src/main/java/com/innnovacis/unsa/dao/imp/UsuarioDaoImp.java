
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.GenerarCodigo;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocenteExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioHome;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
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
public class UsuarioDaoImp implements IUsuarioDao {

    @Inject
    private EntityManager em;
    
    @Inject
    private GenerarCodigo generarCodigo;
    
    @Override
    @Transactional
    public SRIUsuario  Insert(SRIUsuario entidad) {
        try{
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
    public SRIUsuario Update(SRIUsuario entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuario entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return true;
    }

    @Override
    public SRIUsuario GetById(int idEntidad) {
        SRIUsuario entidad = null;
        try{
            entidad = em.createNamedQuery("SRIUsuario.GetById", SRIUsuario.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }
    
    @Override
    public SRIUsuario GetByIdPersona(int idEntidad) {
        SRIUsuario entidad = null;
        try{
            entidad = em.createNamedQuery("SRIUsuario.GetByIdPersona", SRIUsuario.class).setParameter("idEntidad", idEntidad).getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIUsuario> GetAll() {
        List<SRIUsuario> olistaRespuesta = null;
        try {
            olistaRespuesta = em.createNamedQuery("SRIUsuario.GetAll",SRIUsuario.class).getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return olistaRespuesta;
    }

    
    @Override
    public List<SRIUsuarioPersona> GetPagina(SRIPaginacionObject entidad) {
        List<SRIUsuarioPersona> listUsuarios = null;
        try {
            Query query = em.createNativeQuery("{call GetUsuarioPersona(?1,?2,?3)}", SRIUsuarioPersona.class)
                        .setParameter(1, entidad.getFiltro())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
            listUsuarios = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listUsuarios;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalUsuarioPersona(?1)}")
                        .setParameter(1, entidad.getFiltro());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public SRIUsuarioLogin AutenticarUsuario(SRIUsuario entidad) {
        SRIUsuarioLogin usuarioLogin = null;
        try {
            Query query = em.createNativeQuery("{call AutenticarUsuario(?1,?2)}", SRIUsuarioLogin.class)
                        .setParameter(1, entidad.getSUsuarioLogin())
                        .setParameter(2, entidad.getSUsuarioPassword());
            usuarioLogin = (SRIUsuarioLogin)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return usuarioLogin;
    }

    @Override
    public List<SRIUsuarioPersona> GetDestinatariosByCodigoActorDestino(String codigoActorDestino) {
        List<SRIUsuarioPersona> usuariosPersona = null;
        try {
                Query query = em.createNativeQuery("{call GetDestinatariosByCodigoActorDestino(?1)}", SRIUsuarioPersona.class)
                .setParameter(1, codigoActorDestino);
            usuariosPersona = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return usuariosPersona;
    }

    @Override
    public SRIUsuario GetByIdUsuario(int idUsuario) {
        SRIUsuario entidad = null;
        try{
            Query query = em.createNativeQuery("select * from dbunsa.usuario where idusuario = ?", SRIUsuario.class)
                .setParameter(1, idUsuario);
            entidad = (SRIUsuario) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIFlujoActor> GetActoresByIdUsuario(int idUsuario) {
        List<SRIFlujoActor> entidad = null;
        try{
            Query query = em.createNativeQuery("{call GetActoresByIdUsuario(?1)}", SRIFlujoActor.class)
                .setParameter(1, idUsuario);
            entidad = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public List<SRIUsuarioColor> GetUsuariosColor(SRIPaginacionObject entidad) {
        List<SRIUsuarioColor> usuarioColor = null;
        try{
            Query query = em.createNativeQuery("{call GetUsuarioColor(?1, ?2, ?3, ?4, ?5, ?6)}", SRIUsuarioColor.class)
                    .setParameter(1, entidad.getIdFacultad())
                    .setParameter(2, entidad.getIdDepartamento())
                    .setParameter(3, entidad.getFiltro())
                    .setParameter(4, entidad.getIdTipoInvestigacion())
                    .setParameter(5, entidad.getRango())
                    .setParameter(6, entidad.getCurrentPage());
            usuarioColor = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return usuarioColor;
    }

    @Override
    public int GetTotalUsuariosColor(SRIPaginacionObject entidad) {
        BigInteger total = null;
        try{
            Query query = em.createNativeQuery("{call GetTotalUsuarioColor(?1, ?2, ?3, ?4)}")
                    .setParameter(1, entidad.getIdFacultad())
                    .setParameter(2, entidad.getFiltro())
                    .setParameter(3, entidad.getIdTipoInvestigacion())
                    .setParameter(4, entidad.getIdDepartamento());
            total = (BigInteger)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIUsuarioPersona> GetUsuarioPersonaByIdUsuario(int idUsuario) {
        List<SRIUsuarioPersona> usuariosPersona = null;
        try {
                Query query = em.createNativeQuery("{call GetUsuarioPersonaByIdUsuario(?1)}", SRIUsuarioPersona.class)
                .setParameter(1, idUsuario);
            usuariosPersona = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return usuariosPersona;
    }

    @Override
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos(int idSemestre) {
        SRIDocenteActivosInactivos respuesta = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalDocentesActivosInactivos()}", SRIDocenteActivosInactivos.class);
            respuesta = (SRIDocenteActivosInactivos) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivosByFacultad(int idFacultad) {
        SRIDocenteActivosInactivos respuesta = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalDocentesActivosInactivosByFacultad(?1)}", SRIDocenteActivosInactivos.class)
                                .setParameter(1, idFacultad);
            respuesta = (SRIDocenteActivosInactivos) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioHome> GetUsuarioHome(int idUsuario, int idUsuarioDirector) {
        List<SRIUsuarioHome> respuesta = null;
        try {
            Query query = em.createNativeQuery("{call GetUsuarioHome(?1, ?2)}", SRIUsuarioHome.class)
                                .setParameter(1, idUsuario)
                                .setParameter(2, idUsuarioDirector);
            respuesta = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public SRIUsuario enviarCodigo(int idUsuario) {
        SRIUsuario entidad = null;
        try{
            entidad = em.createNamedQuery("SRIUsuario.GetById", SRIUsuario.class).setParameter("idEntidad", idUsuario).getSingleResult();
            entidad.setSCodigo(generarCodigo.getCadenaAlfaNumerica(6));
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public SRIUsuario verificarCodigo(SRIUsuario entidad) {
        SRIUsuario respuesta = null;
        try {
            Query query = em.createNativeQuery("{call VerificarCodigo(?1,?2)}", SRIUsuario.class)
                        .setParameter(1, entidad.getNIdUsuario())
                        .setParameter(2, entidad.getSCodigo());
            respuesta = (SRIUsuario)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioPersona> GetDirectorDepartamentoByIdDocente(int idUsuario) {
        List<SRIUsuarioPersona> lstUsuarioPersona = null;
        try {
            Query query = em.createNativeQuery("{call GetDirectorDepartamentoByIdDocente(?1)}", SRIUsuarioPersona.class)
                        .setParameter(1, idUsuario);
            lstUsuarioPersona = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return lstUsuarioPersona;
    }
    
    @Override
    public List<SRIUsuarioPersona> GetDirectorUnidadByIdDocente(int idUsuario) {
        List<SRIUsuarioPersona> lstUsuarioPersona = null;
        try {
            Query query = em.createNativeQuery("{call GetDirectorUnidadByIdDocente(?1)}", SRIUsuarioPersona.class)
                        .setParameter(1, idUsuario);
            lstUsuarioPersona = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return lstUsuarioPersona;
    }

    @Override
    public SRIDocente GetDocenteReporte(int idUsuario) {
        SRIDocente docente = null;
        try {
            Query query = em.createNativeQuery("{call GetDocenteReporte(?1)}", SRIDocente.class)
                        .setParameter(1, idUsuario);
            docente = (SRIDocente)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return docente;
    }
    
    @Override
    public List<SRIDocenteExoneracion> GetListaUsuarioExoneracion(SRIPaginacionObject entidad) {
        List<SRIDocenteExoneracion> listUsuarios = null;
        try {
            Query query = em.createNativeQuery("{call GetUsuarioExonerados(?1,?2,?3,?4)}", SRIDocenteExoneracion.class)
                        .setParameter(1, entidad.getFiltro())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage())
                        .setParameter(4, entidad.getIdSemestre());
            listUsuarios = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listUsuarios;
    }

    @Override
    public int GetTotalUsuarioExoneracion(SRIPaginacionObject entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalUsuarioExonerados(?1)}")
                        .setParameter(1, entidad.getFiltro());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public SRIUsuarioLogin LoginGoogle(String userEmail) {
        SRIUsuarioLogin usuarioLogin = null;
        try {
            Query query = em.createNativeQuery("{call AutenticarEmail(?1)}", SRIUsuarioLogin.class)
                        .setParameter(1, userEmail);
            usuarioLogin = (SRIUsuarioLogin)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return usuarioLogin;
    }

}
