
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IRolBusiness;
import com.innnovacis.unsa.dao.IRolDao;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
import com.innnovacis.unsa.model.SRIRol;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class RolBusinessImp implements IRolBusiness {

    @Inject
    private IRolDao rolDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIRol entidad) {
        int id = -1;
        try{
            entidad = rolDao.Insert(entidad);
            id = entidad.getNIdRol();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIRol entidad) {
        boolean respuesta = false;
         try{
            rolDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIRol entidad) {
        boolean respuesta = false;
         try{
            rolDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIRol Get(int idEntidad) {
        SRIRol respuesta = null;
         try{
            respuesta = rolDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIRol> GetAll() {
         List<SRIRol> respuesta = null;
         try{
            respuesta = rolDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
