
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.IEstructuraAreaInvestigacionDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class FlujoAristaBusinessImp implements IActividadInvestigacionBusiness {

    @Inject
    private IActividadInvestigacionDao actividadInvestigacionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIActividadInvestigacion entidad) {
        int id = -1;
        try{
            entidad = actividadInvestigacionDao.Insert(entidad);
            id = entidad.getNIdActividadInvestigacion();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            actividadInvestigacionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            actividadInvestigacionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIActividadInvestigacion Get(int idEntidad) {
        SRIActividadInvestigacion respuesta = null;
         try{
            respuesta = actividadInvestigacionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIActividadInvestigacion> GetAll() {
         List<SRIActividadInvestigacion> respuesta = null;
         try{
            respuesta = actividadInvestigacionDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
