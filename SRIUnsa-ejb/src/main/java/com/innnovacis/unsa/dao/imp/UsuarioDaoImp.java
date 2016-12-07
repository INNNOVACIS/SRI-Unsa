
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIPaginacionObject;
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
    public List<SRIUsuario> GetByIdActorDestino(int idActorDestino) {
        List<SRIUsuario> listUsuarios = null;
        try {
            Query query = em.createNativeQuery("{call GetByIdActorDestino(?1)}", SRIUsuario.class)
                .setParameter(1, idActorDestino);
            listUsuarios = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return listUsuarios;
    }

}
