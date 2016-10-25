
package com.innnovacis.unsa.borrar;

import com.innnovacis.unsa.borrar.IPersonaDao;
import com.innnovacis.unsa.modelborrar.Persona;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaUpdate;
import javax.transaction.Transactional;


@Dependent
public class PersonaDaoImp implements IPersonaDao {

    @Inject
    private EntityManager em;

    public Persona findById(int id) {
        return em.find(Persona.class, id);
    }

    public List<Persona> findAll() {
        
        
/**ACA ESTAS USANDO CRITERIOS DE HIBERNATE, USA TAMBIEN PROCEDMIENTO ALMACENADOS*/ 
/*LECTURA DE UN PROCEDIMIENTO ALMACENADO*/
        

//        Query query = em.createNativeQuery("{call getNombreById(1)}");
//        List<String> list=query.getResultList();
//        System.out.println("=====> " + list);
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = cb.createQuery(Persona.class);
        Root<Persona> persona = criteria.from(Persona.class);
        criteria.select(persona).orderBy(cb.asc(persona.get("nombre")));
        
        
        
        return em.createQuery(criteria).getResultList();
    }
    
    
    @Transactional
    public int deleteById(int id){
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate update = cb.createCriteriaUpdate(Persona.class); // create update
        Root e = update.from(Persona.class); // set the root class
        update.set("estado", 0);                             // set update
        update.where(cb.equal(e.get("idPersona"), id));  //  where clause
        Query query = em.createQuery(update);           // perform update
        int rowCount = query.executeUpdate();
        return rowCount;
    }
    
    
    @Transactional
    public int UpdateD(Persona persona){
        // manda objeto persona con datos antiguos
        // update user datas
//        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate updateCriteria = cb.createCriteriaUpdate(Persona.class);
        Root root = updateCriteria.from(Persona.class);
        // update email
        
        updateCriteria.set("nombre", persona.getNombre());        
        
        // set where clause
        updateCriteria.where(cb.equal(root.get("idPersona"), persona.getIdPersona()));
        // update
        Query query = em.createQuery(updateCriteria); 
        int affected = query.executeUpdate();
        System.out.println("Affected row: " + affected);
        // em.getTransaction().commit();
        // end update user date of birth
        // set update
        return affected;
    }
}
