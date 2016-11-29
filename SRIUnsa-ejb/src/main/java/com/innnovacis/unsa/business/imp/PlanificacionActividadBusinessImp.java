
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IPlanificacionActividadBusiness;
import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class PlanificacionActividadBusinessImp implements IPlanificacionActividadBusiness {

    @Inject
    private IPlanificacionActividadDao planificacionActividadDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIPlanificacionActividad entidad) {
        int id = -1;
        try{
            entidad = planificacionActividadDao.Insert(entidad);
            id = entidad.getNIdPlanificacionActividad();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIPlanificacionActividad entidad) {
        boolean respuesta = false;
         try{
            planificacionActividadDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIPlanificacionActividad entidad) {
        boolean respuesta = false;
         try{
            planificacionActividadDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIPlanificacionActividad Get(int idEntidad) {
        SRIPlanificacionActividad respuesta = null;
         try{
            respuesta = planificacionActividadDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIPlanificacionActividad> GetAll() {
         List<SRIPlanificacionActividad> respuesta = null;
         try{
            respuesta = planificacionActividadDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
