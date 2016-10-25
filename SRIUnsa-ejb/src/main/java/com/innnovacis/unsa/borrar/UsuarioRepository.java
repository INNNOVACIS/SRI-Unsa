/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.borrar;

import com.innnovacis.unsa.modelborrar.Usuario;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author innnovacis
 */

@ApplicationScoped
public class UsuarioRepository {

    @Inject
    private EntityManager em;
    
    @Inject
    private Event<Usuario> usuarioEventSrc;
    
    public List<Usuario> getUsuario(){
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot = criteria.from(Usuario.class);
        criteria.select(usuarioRoot);
        
        return em.createQuery(criteria).getResultList(); 
    }
    
    @Transactional
    public Usuario saveUsuario(Usuario usuario){
        em.persist(usuario);
        usuarioEventSrc.fire(usuario);
        return usuario;
    }
    
    @Transactional
    public Usuario updateUsuario(Usuario usuario){
        em.merge(usuario);
        return usuario;
    }
    
    @Transactional
    public Usuario deleteUsuario(Usuario usuario){
        em.merge(usuario);
        return usuario;
    }
    
    public Usuario autenticarUsuario(Usuario usuario) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot = criteria.from(Usuario.class);
        criteria.select(usuarioRoot);
        criteria.where(cb.equal(usuarioRoot.get("usuario"), usuario.getUsuario()));     
        
        return em.createQuery(criteria).getSingleResult();
    }

}
