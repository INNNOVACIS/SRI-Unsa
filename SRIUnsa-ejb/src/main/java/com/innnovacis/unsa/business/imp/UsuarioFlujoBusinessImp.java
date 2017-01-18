
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IUsuarioFlujoBusiness;
import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.SRIFlujoActorUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioFlujoUtil;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class UsuarioFlujoBusinessImp implements IUsuarioFlujoBusiness {

    @Inject
    private IUsuarioFlujoDao usuarioFlujoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(List<SRIUsuarioFlujo> entidad) {
        int id = -1;
        SRIUsuarioFlujo usuarioFlujo = null;
        try{
            for(int i = 0; i < entidad.size(); i++){
                usuarioFlujo = usuarioFlujoDao.Insert(entidad.get(i));
                id = usuarioFlujo.getNIdUsuarioFlujo();
            }  
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIUsuarioFlujo entidad) {
        boolean respuesta = false;
         try{
            usuarioFlujoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIUsuarioFlujo entidad) {
        boolean respuesta = false;
         try{
            usuarioFlujoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIUsuarioFlujo Get(int idEntidad) {
        SRIUsuarioFlujo respuesta = null;
         try{
            respuesta = usuarioFlujoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuarioFlujo> GetAll() {
        List<SRIUsuarioFlujo> respuesta = null;
        try{
            respuesta = usuarioFlujoDao.GetAll();
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = usuarioFlujoDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioFlujoUtil> GetPagina(SRIPaginacionObject object) {
        List<SRIUsuarioFlujoUtil> respuesta = null;
        try{
            respuesta = usuarioFlujoDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioFlujo> getUsuarioFlujoByIdUsuario(int id) {
        List<SRIUsuarioFlujo> respuesta = null;
        try{
            respuesta = usuarioFlujoDao.getUsuarioFlujoByIdUsuario(id);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIFlujoActorUtil> getUsuarioFlujoActorByIdUsuario(int id) {
        List<SRIFlujoActorUtil> respuesta = null;
        try{
            respuesta = usuarioFlujoDao.getUsuarioFlujoActorByIdUsuario(id);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public int CreateAndGetUsuarioFlujo(SRIUsuarioFlujo entidad) {
        int respuesta = -1;
        try{
            respuesta = usuarioFlujoDao.CreateAndGetUsuarioFlujo(entidad);
        }
        catch(Exception ex){
            System.out.println("Error ===> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public boolean updateUsuarioFlujo(List<SRIUsuarioFlujo> entidad) {
        boolean respuesta = false;
        List<SRIUsuarioFlujo> lstUsuarioFlujo = null;
        SRIUsuarioFlujo usuarioFlujo = null;
        try{
            lstUsuarioFlujo = usuarioFlujoDao.getUsuarioFlujoByIdUsuario(entidad.get(0).getNIdUsuario());
            for(int i = 0; i < lstUsuarioFlujo.size(); i++){
                boolean respuestaDelete = usuarioFlujoDao.Delete(lstUsuarioFlujo.get(i));
            }
            for(int i = 0; i < entidad.size(); i++){
                usuarioFlujo = usuarioFlujoDao.Insert(entidad.get(i));
            }
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    
}
