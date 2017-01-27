
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IRolBusiness;
import com.innnovacis.unsa.dao.IRolDao;
import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
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

    @Override
    public List<SRIRol> GetPagina(SRIPaginacionObject object) {
        List<SRIRol> respuesta = null;
         try{
            respuesta = rolDao.GetPagina(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
         try{
            respuesta = rolDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    
}
