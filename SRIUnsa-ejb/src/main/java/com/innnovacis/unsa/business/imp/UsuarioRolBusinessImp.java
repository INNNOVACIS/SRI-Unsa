
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IUsuarioRolBusiness;
import com.innnovacis.unsa.dao.IUsuarioRolDao;
import com.innnovacis.unsa.model.SRIUsuarioRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import com.innnovacis.unsa.util.SRIUsuarioRolesUtil;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class UsuarioRolBusinessImp implements IUsuarioRolBusiness {

    @Inject
    private IUsuarioRolDao usuarioRolDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIUsuarioRol entidad) {
        int id = -1;
        try{
            entidad = usuarioRolDao.Insert(entidad);
            id = entidad.getNIdUsuarioRol();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIUsuarioRol entidad) {
        boolean respuesta = false;
         try{
            usuarioRolDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIUsuarioRol entidad) {
        boolean respuesta = false;
         try{
            usuarioRolDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIUsuarioRol Get(int idEntidad) {
        SRIUsuarioRol respuesta = null;
         try{
            respuesta = usuarioRolDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuarioRol> GetAll() {
         List<SRIUsuarioRol> respuesta = null;
         try{
            respuesta = usuarioRolDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = usuarioRolDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioRolUtil> GetPagina(SRIPaginacionObject object) {
        List<SRIUsuarioRolUtil> respuesta = null;
        try{
            respuesta = usuarioRolDao.GetPagina(object);
        }
        catch(Exception ex){
            System.out.println("Error ========> " + ex.getMessage());
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuarioRolesUtil> getUsuarioRolByIdUsuario(int id) {
        List<SRIUsuarioRolesUtil> respuesta = null;
        try{
            respuesta = usuarioRolDao.getUsuarioRolByIdUsuario(id);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    
}
