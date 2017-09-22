
package com.innnovacis.unsa.business.imp;

import com.innnovacis.unsa.business.IExoneracionBusiness;
import com.innnovacis.unsa.dao.IExoneracionDao;
import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.dao.IUsuarioExoneracionDao;
import com.innnovacis.unsa.model.SRIExoneracion;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.model.SRIUsuarioExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class ExoneracionBusinessImp implements IExoneracionBusiness {

    @Inject  
    private IExoneracionDao exoneracionDao;
    
    @Inject  
    private IUsuarioDao usuarioDao;
    
    @Inject  
    private IUsuarioExoneracionDao usuarioExoneracionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIExoneracion entidad) {
        int id = -1;
        try{
            entidad = exoneracionDao.Insert(entidad);
            id = entidad.getNIdExoneracion();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIExoneracion entidad) {
        boolean respuesta = false;
         try{
            exoneracionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIExoneracion entidad) {
        boolean respuesta = false;
         try{
            exoneracionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRIExoneracion Get(int idEntidad) {
        SRIExoneracion respuesta = null;
         try{
            respuesta = exoneracionDao.GetById(idEntidad);
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public List<SRIExoneracion> GetAll() {
         List<SRIExoneracion> respuesta = null;
         try{
            respuesta = exoneracionDao.GetAll();
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRIExoneracion GetById(int id) {
        SRIExoneracion respuesta = null;
        try{
            respuesta = exoneracionDao.GetById(id);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = exoneracionDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIExoneracion> GetPagina(SRIPaginacionObject object) {
        List<SRIExoneracion> respuesta = null;
        try{
            respuesta = exoneracionDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public SRIUsuario RegistrarUsuarioExoneracion(SRIUsuarioExoneracion usuarioExoneracion) {
        try{
            SRIUsuario usuario = usuarioDao.GetByIdPersona(usuarioExoneracion.getNIdUsuario()); //enviamos el idpersona camuflado en el idusuario
            usuarioExoneracion.setNIdUsuario(usuario.getNIdUsuario());
            SRIUsuarioExoneracion oUsuarioExoneracion = usuarioExoneracionDao.GetByIdUsuarioIdSemestre(
                                        usuario.getNIdUsuario(),
                                        usuarioExoneracion.getNIdSemestre());
            
            if(oUsuarioExoneracion == null){
                oUsuarioExoneracion = usuarioExoneracionDao.Insert(usuarioExoneracion);
                return usuario;
            } else {
                return new SRIUsuario();
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public SRIUsuario DeleteUsuarioExoneracion(int idUsuarioExoneracion) {
        SRIUsuario respuesta = new SRIUsuario();
        try{
            SRIUsuarioExoneracion usuarioExoneracion = usuarioExoneracionDao.GetById(idUsuarioExoneracion);
            usuarioExoneracionDao.Delete(usuarioExoneracion);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }
}
