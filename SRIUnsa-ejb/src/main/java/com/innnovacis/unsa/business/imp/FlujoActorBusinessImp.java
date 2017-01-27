
package com.innnovacis.unsa.business.imp;

import com.innnovacis.unsa.business.IFlujoActorBusiness;
import com.innnovacis.unsa.dao.IFlujoActorDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

////////

@RequestScoped
public class FlujoActorBusinessImp implements IFlujoActorBusiness {

    @Inject
    private IFlujoActorDao flujoActorDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIFlujoActor entidad) {
        int id = -1;
        try{
            entidad = flujoActorDao.Insert(entidad);
            id = entidad.getNIdFlujoActor();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIFlujoActor entidad) {
        boolean respuesta = false;
         try{
            flujoActorDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIFlujoActor entidad) {
        boolean respuesta = false;
         try{
            flujoActorDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIFlujoActor Get(int idEntidad) {
        SRIFlujoActor respuesta = null;
         try{
            respuesta = flujoActorDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIFlujoActor> GetAll() {
         List<SRIFlujoActor> respuesta = null;
         try{
            respuesta = flujoActorDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = flujoActorDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIFlujoActor> GetPagina(SRIPaginacionObject object) {
        List<SRIFlujoActor> respuesta = null;
        try{
            respuesta = flujoActorDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    
}
