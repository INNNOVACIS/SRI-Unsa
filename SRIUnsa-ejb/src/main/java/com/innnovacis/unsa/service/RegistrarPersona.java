
package com.innnovacis.unsa.service;

import com.innnovacis.unsa.modelborrar.Member;
import com.innnovacis.unsa.modelborrar.Persona;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class RegistrarPersona {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Persona> personaEventSrc;

    public void registrar(Persona persona) throws Exception {
        log.info("Registrando a " + persona.getNombre());
        em.merge(persona);
        personaEventSrc.fire(persona);
    }
}
