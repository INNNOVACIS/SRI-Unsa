
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class UsuarioDaoImp implements IUsuarioDao {

    @Inject
    private EntityManager em;
    
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
    public SRIUsuarioRolUtil AutenticarUsuario(SRIUsuario entidad) {
        SRIUsuarioRolUtil objUsuarioRol = null;
        try {
            Query query = em.createNativeQuery("{call AutenticarUsuario(?1,?2)}", SRIUsuarioRolUtil.class)
                        .setParameter(1, entidad.getSUsuarioLogin())
                        .setParameter(2, entidad.getSUsuarioPassword());
            objUsuarioRol = (SRIUsuarioRolUtil)query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return objUsuarioRol;
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
            Query query = em.createNativeQuery("{call GetUsuarioColor(?1, ?2, ?3, ?4, ?5)}", SRIUsuarioColor.class)
                    .setParameter(1, entidad.getIdFacultad())
                    .setParameter(2, entidad.getFiltro())
                    .setParameter(3, entidad.getIdTipoInvestigacion())
                    .setParameter(4, entidad.getRango())
                    .setParameter(5, entidad.getCurrentPage());
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
            Query query = em.createNativeQuery("{call GetTotalUsuarioColor(?1, ?2, ?3)}")
                    .setParameter(1, entidad.getIdFacultad())
                    .setParameter(2, entidad.getFiltro())
                    .setParameter(3, entidad.getIdTipoInvestigacion());
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
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos() {
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

}
