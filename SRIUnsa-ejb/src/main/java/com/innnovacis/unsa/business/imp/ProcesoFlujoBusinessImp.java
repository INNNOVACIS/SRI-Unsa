
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IProcesoFlujoBusiness;
import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.model.SRIProcesoFlujo;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ProcesoFlujoBusinessImp implements IProcesoFlujoBusiness {

    @Inject
    private IProcesoFlujoDao procesoFlujoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIProcesoFlujo entidad) {
        int id = -1;
        try{
            entidad = procesoFlujoDao.Insert(entidad);
            id = entidad.getNIdProcesoFlujo();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIProcesoFlujo entidad) {
        boolean respuesta = false;
         try{
            procesoFlujoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIProcesoFlujo entidad) {
        boolean respuesta = false;
         try{
            procesoFlujoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIProcesoFlujo Get(int idEntidad) {
        SRIProcesoFlujo respuesta = null;
         try{
            respuesta = procesoFlujoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIProcesoFlujo> GetAll() {
         List<SRIProcesoFlujo> respuesta = null;
         try{
            respuesta = procesoFlujoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
