
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IRolPrivilegioBusiness;
import com.innnovacis.unsa.dao.IRolPrivilegioDao;
import com.innnovacis.unsa.model.SRIRolPrivilegio;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class RolPrivilegioBusinessImp implements IRolPrivilegioBusiness {

    @Inject
    private IRolPrivilegioDao rolPrivilegioDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIRolPrivilegio entidad) {
        int id = -1;
        try{
            entidad = rolPrivilegioDao.Insert(entidad);
            id = entidad.getNIdRol();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIRolPrivilegio entidad) {
        boolean respuesta = false;
         try{
            rolPrivilegioDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIRolPrivilegio entidad) {
        boolean respuesta = false;
         try{
            rolPrivilegioDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIRolPrivilegio Get(int idEntidad) {
        SRIRolPrivilegio respuesta = null;
         try{
            respuesta = rolPrivilegioDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIRolPrivilegio> GetAll() {
         List<SRIRolPrivilegio> respuesta = null;
         try{
            respuesta = rolPrivilegioDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
