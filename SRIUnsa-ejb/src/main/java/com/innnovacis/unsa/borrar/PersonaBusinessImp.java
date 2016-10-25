
package com.innnovacis.unsa.borrar;

import com.innnovacis.unsa.borrar.IPersonaDao;
import com.innnovacis.unsa.modelborrar.Persona;
import javax.inject.Inject;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class PersonaBusinessImp implements IPersonaBusiness {

    @Inject
    private IPersonaDao personaDao;

    @Override
    public Persona findById(int id) {
        return personaDao.findById(id);
    }

    @Override
    public List<Persona> findAll() {
        return personaDao.findAll();
    }
    
    
}
