
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.business.IEstadoBusiness;
import com.innnovacis.unsa.dao.IEstadoDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIEstado;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class EstadoBusinessImp implements IEstadoBusiness {

    @Inject
    private IEstadoDao estadoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIEstado entidad) {
        int id = -1;
        try{
            entidad = estadoDao.Insert(entidad);
            id = entidad.getNIdEstado();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIEstado entidad) {
        boolean respuesta = false;
         try{
            estadoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIEstado entidad) {
        boolean respuesta = false;
         try{
            estadoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIEstado Get(int idEntidad) {
        SRIEstado respuesta = null;
         try{
            respuesta = estadoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIEstado> GetAll() {
         List<SRIEstado> respuesta = null;
         try{
            respuesta = estadoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
