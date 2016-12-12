
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IPersonaBusiness;
import com.innnovacis.unsa.dao.IPersonaDao;
import com.innnovacis.unsa.model.SRIPersona;

import javax.inject.Inject;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class PersonaBusinessImp implements IPersonaBusiness {
    
    @Inject
    private IPersonaDao personaDao;

    @Override
    public int Insertar(SRIPersona entidad) {
        int id = -1;
        try{
            entidad = personaDao.Insert(entidad);
            id = entidad.getNIdPersona();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIPersona entidad) {
        boolean respuesta = false;
        try{
            personaDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIPersona entidad) {
        boolean respuesta = false;
        try{
            personaDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRIPersona Get(int idEntidad) {
        SRIPersona respuesta = null;
        try{
            respuesta = personaDao.GetById(idEntidad);
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public List<SRIPersona> GetAll() {
        List<SRIPersona> respuesta = null;
         try{
            respuesta = personaDao.GetAll();
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

}
