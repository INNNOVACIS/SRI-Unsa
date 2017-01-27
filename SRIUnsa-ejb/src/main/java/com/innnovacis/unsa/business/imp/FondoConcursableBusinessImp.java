
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IFondoConcursableBusiness;
import com.innnovacis.unsa.dao.IFondoConcursableDao;
import com.innnovacis.unsa.model.SRIFondoConcursable;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class FondoConcursableBusinessImp implements IFondoConcursableBusiness {

    @Inject
    private IFondoConcursableDao fondoConcursableDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIFondoConcursable entidad) {
        int id = -1;
        try{
            entidad = fondoConcursableDao.Insert(entidad);
            id = entidad.getNIdFondoConcursable();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIFondoConcursable entidad) {
        boolean respuesta = false;
         try{
            fondoConcursableDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIFondoConcursable entidad) {
        boolean respuesta = false;
         try{
            fondoConcursableDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIFondoConcursable Get(int idEntidad) {
        SRIFondoConcursable respuesta = null;
         try{
            respuesta = fondoConcursableDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIFondoConcursable> GetAll() {
        log.info("PRUEBAAAA LOGGGG");
         List<SRIFondoConcursable> respuesta = null;
         try{
            respuesta = fondoConcursableDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
       int respuesta = -1;
         try{
            respuesta = fondoConcursableDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
            
        }
        return respuesta;
    }

    @Override
    public List<SRIFondoConcursable> GetPagina(SRIPaginacionObject object) {
        List<SRIFondoConcursable> respuesta = null;
         try{
            respuesta = fondoConcursableDao.GetPagina(object);
        }
        catch(Exception ex){
            
        }
        return respuesta;
    }

    
}
