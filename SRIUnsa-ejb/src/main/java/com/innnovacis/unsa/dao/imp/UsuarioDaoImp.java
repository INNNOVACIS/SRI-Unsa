
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import com.innnovacis.unsa.util.SRIUsuariosPaginacion;
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
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIUsuario Update(SRIUsuario entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuario entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuario GetById(int idEntidad) {
        SRIUsuario entidad = em.createNamedQuery("SRIUsuario.GetById", SRIUsuario.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuario> GetAll() {
        List<SRIUsuario> olistaRespuesta = em.createNamedQuery("SRIUsuario.GetAll",SRIUsuario.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public SRIUsuario Autenticar(SRIUsuario entidad) {
        
        SRIUsuario usuario = em.createNamedQuery("SRIUsuario.Autenticar", SRIUsuario.class)
                .setParameter("usuario", entidad.getSUsuarioLogin())
                .setParameter("password", entidad.getSUsuarioPassword())
                .getSingleResult();
        
        return usuario;
    }
    
    @Override
    public List<SRIUsuario> GetPagina(SRIUsuariosPaginacion entidad) {
        Query query = em.createNativeQuery("{call usuarioPaginacion(?1,?2,?3)}", SRIUsuario.class)
                        .setParameter(1, entidad.getFiltro().getSUsuarioLogin())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
        List<SRIUsuario> listUsuarios = query.getResultList();
        return listUsuarios;
    }

    @Override
    public int GetTotalPaginacion(SRIUsuariosPaginacion entidad) {
        Query query = em.createNativeQuery("{call total_usuarios(?1)}")
                        .setParameter(1, entidad.getFiltro().getSUsuarioLogin());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public SRIUsuarioRolUtil AutenticarUsuario(SRIUsuario entidad) {
        Query query = em.createNativeQuery("{call AutenticarUsuario(?1,?2)}", SRIUsuarioRolUtil.class)
                        .setParameter(1, entidad.getSUsuarioLogin())
                        .setParameter(2, entidad.getSUsuarioPassword());
        SRIUsuarioRolUtil objUsuarioRol = (SRIUsuarioRolUtil)query.getSingleResult();
        return objUsuarioRol;
    }

}
