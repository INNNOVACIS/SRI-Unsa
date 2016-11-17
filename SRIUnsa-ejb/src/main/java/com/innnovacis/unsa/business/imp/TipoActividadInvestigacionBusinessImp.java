
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.ITipoActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.ITipoActividadInvestigacionDao;
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class TipoActividadInvestigacionBusinessImp implements ITipoActividadInvestigacionBusiness {

    @Inject
    private ITipoActividadInvestigacionDao tipoActividadInvestigacionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRITipoActividadInvestigacion entidad) {
        int id = -1;
        try{
            entidad = tipoActividadInvestigacionDao.Insert(entidad);
            id = entidad.getNIdTipoActividadInvestigacion();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRITipoActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            tipoActividadInvestigacionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRITipoActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            tipoActividadInvestigacionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRITipoActividadInvestigacion Get(int idEntidad) {
        SRITipoActividadInvestigacion respuesta = null;
         try{
            respuesta = tipoActividadInvestigacionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoActividadInvestigacion> GetAll() {
      
         List<SRITipoActividadInvestigacion> respuesta = null;
         try{
            respuesta = tipoActividadInvestigacionDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = tipoActividadInvestigacionDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoActividadInvestigacion> GetPagina(SRIPaginacionObject object) {
        List<SRITipoActividadInvestigacion> respuesta = null;
        try{
            respuesta = tipoActividadInvestigacionDao.GetPagina(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
