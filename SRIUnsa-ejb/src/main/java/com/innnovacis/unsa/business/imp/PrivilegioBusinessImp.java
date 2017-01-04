
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IPrivilegioBusiness;
import com.innnovacis.unsa.dao.IPrivilegioDao;
import com.innnovacis.unsa.model.SRIPrivilegio;
import com.innnovacis.unsa.util.SRIPaginacionObject;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class PrivilegioBusinessImp implements IPrivilegioBusiness {

    @Inject
    private IPrivilegioDao privilegioDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIPrivilegio entidad) {
        int id = -1;
        try{
            entidad = privilegioDao.Insert(entidad);
            id = entidad.getNIdPrivilegio();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIPrivilegio entidad) {
        boolean respuesta = false;
         try{
            privilegioDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIPrivilegio entidad) {
        boolean respuesta = false;
         try{
            privilegioDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIPrivilegio Get(int idEntidad) {
        SRIPrivilegio respuesta = null;
         try{
            respuesta = privilegioDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIPrivilegio> GetAll() {
         List<SRIPrivilegio> respuesta = null;
         try{
            respuesta = privilegioDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
         try{
            respuesta = privilegioDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<SRIPrivilegio> GetPagina(SRIPaginacionObject object) {
        List<SRIPrivilegio> respuesta = null;
         try{
            respuesta = privilegioDao.GetPagina(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<SRIPrivilegio> GetPrivilegiosByIdUsuario(int id) {
        List<SRIPrivilegio> respuesta = null;
        try{
            respuesta = privilegioDao.GetPrivilegiosByIdUsuario(id);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    
}
