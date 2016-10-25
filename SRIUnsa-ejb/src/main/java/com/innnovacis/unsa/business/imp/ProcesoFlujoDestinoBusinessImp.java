
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IProcesoFlujoDestinoBusiness;
import com.innnovacis.unsa.dao.IProcesoFlujoDestinoDao;
import com.innnovacis.unsa.model.SRIProcesoFlujoDestino;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ProcesoFlujoDestinoBusinessImp implements IProcesoFlujoDestinoBusiness {

    @Inject
    private IProcesoFlujoDestinoDao procesoFlujoDestinoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIProcesoFlujoDestino entidad) {
        int id = -1;
        try{
            entidad = procesoFlujoDestinoDao.Insert(entidad);
            id = entidad.getNIdProcesoFlujoDestino();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIProcesoFlujoDestino entidad) {
        boolean respuesta = false;
         try{
            procesoFlujoDestinoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIProcesoFlujoDestino entidad) {
        boolean respuesta = false;
         try{
            procesoFlujoDestinoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIProcesoFlujoDestino Get(int idEntidad) {
        SRIProcesoFlujoDestino respuesta = null;
         try{
            respuesta = procesoFlujoDestinoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIProcesoFlujoDestino> GetAll() {
         List<SRIProcesoFlujoDestino> respuesta = null;
         try{
            respuesta = procesoFlujoDestinoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
