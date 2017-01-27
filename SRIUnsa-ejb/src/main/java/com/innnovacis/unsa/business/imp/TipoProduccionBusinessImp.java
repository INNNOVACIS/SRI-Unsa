
package com.innnovacis.unsa.business.imp;

import com.innnovacis.unsa.business.ITipoProduccionBusiness;

import com.innnovacis.unsa.dao.ITipoProduccionDao;
import com.innnovacis.unsa.model.SRITipoProduccion;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class TipoProduccionBusinessImp implements ITipoProduccionBusiness {

    @Inject
    private ITipoProduccionDao tipoProduccionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRITipoProduccion entidad) {
        int id = -1;
        try{
            entidad = tipoProduccionDao.Insert(entidad);
            id = entidad.getNIdTipoProduccion();
                    
        }
        catch(Exception ex){
            System.out.println("Error ====> " + ex.getMessage());
        }
        return id;
    }

    @Override
    public boolean Update(SRITipoProduccion entidad) {
        boolean respuesta = false;
         try{
            tipoProduccionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRITipoProduccion entidad) {
        boolean respuesta = false;
         try{
            tipoProduccionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRITipoProduccion Get(int idEntidad) {
        SRITipoProduccion respuesta = null;
         try{
            respuesta = tipoProduccionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoProduccion> GetAll() {
        log.info("PRUEBAAAA LOGGGG");
        List<SRITipoProduccion> respuesta = null;
        try{
            respuesta = tipoProduccionDao.GetAll();
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = tipoProduccionDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRITipoProduccion> GetPagina(SRIPaginacionObject object) {
        List<SRITipoProduccion> respuesta = null;
        try{
            respuesta = tipoProduccionDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    
}
