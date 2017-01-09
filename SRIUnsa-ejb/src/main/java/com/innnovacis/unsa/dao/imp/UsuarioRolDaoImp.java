
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IUsuarioRolDao;
import com.innnovacis.unsa.model.SRIUsuarioRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import com.innnovacis.unsa.util.SRIUsuarioRolesUtil;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;



@Dependent
public class UsuarioRolDaoImp implements IUsuarioRolDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIUsuarioRol  Insert(SRIUsuarioRol entidad) {
        SRIUsuarioRol find = em.createNamedQuery("FindByIdUsuarioIdRol", SRIUsuarioRol.class)
                               .setParameter("idUsuario", entidad.getNIdUsuario())
                               .setParameter("idRol", entidad.getNIdRol()).getSingleResult();
        
        if(find == null){
            entidad.setDFechaCreacion(new Date());
            entidad.setDFechaModificacion(new Date());
            em.persist(entidad);
        } 
        return entidad;
    }

    @Override
    @Transactional
    public SRIUsuarioRol Update(SRIUsuarioRol entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIUsuarioRol entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRIUsuarioRol GetById(int idEntidad) {
        SRIUsuarioRol entidad = em.createNamedQuery("SRIUsuarioRol.GetById", SRIUsuarioRol.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRIUsuarioRol> GetAll() {
        List<SRIUsuarioRol> olistaRespuesta = em.createNamedQuery("SRIUsuarioRol.GetAll",SRIUsuarioRol.class).getResultList();
        return olistaRespuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call usuarioRolTotalPaginacion(?1)}")
                        .setParameter(1, object.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRIUsuarioRolUtil> GetPagina(SRIPaginacionObject object) {
        Query query = em.createNativeQuery("{call usuarioRolPaginacion(?1,?2,?3)}", SRIUsuarioRolUtil.class)
                        .setParameter(1, object.getFiltro())
                        .setParameter(2, object.getRango())
                        .setParameter(3, object.getCurrentPage());
        List<SRIUsuarioRolUtil> listUsuarioRol = query.getResultList();
        return listUsuarioRol;
    }

    @Override
    public List<SRIUsuarioRolesUtil> getUsuarioRolByIdUsuario(int id) {
        Query query = em.createNativeQuery("{call getUsuarioRolByidUsuario(?1)}", SRIUsuarioRolesUtil.class)
                        .setParameter(1, id);
        List<SRIUsuarioRolesUtil> listUsuarioRol = query.getResultList();
        return listUsuarioRol;
    }

}
