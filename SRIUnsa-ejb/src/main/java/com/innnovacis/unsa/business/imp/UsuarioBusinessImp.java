
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IUsuarioBusiness;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import com.innnovacis.unsa.util.SRIUsuariosPaginacion;

import javax.inject.Inject;
import java.util.List;
import javax.enterprise.context.Dependent;



@Dependent
public class UsuarioBusinessImp implements IUsuarioBusiness {

    @Inject
    private IUsuarioDao usuarioDao;

    @Override
    public int Insertar(SRIUsuario entidad) {
        int id = -1;
        try{
            entidad = usuarioDao.Insert(entidad);
            id = entidad.getNIdUsuario();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIUsuario entidad) {
        boolean respuesta = false;
        try{
            usuarioDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIUsuario entidad) {
        boolean respuesta = false;
        try{
            usuarioDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRIUsuario Get(int idEntidad) {
        SRIUsuario respuesta = null;
        try{
            respuesta = usuarioDao.GetById(idEntidad);
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuario> GetAll() {
        List<SRIUsuario> respuesta = null;
         try{
            respuesta = usuarioDao.GetAll();
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }


    @Override
    public List<SRIUsuario> GetPagina(SRIUsuariosPaginacion entidad) {
        List<SRIUsuario> respuesta = null;
         try{
            respuesta = usuarioDao.GetPagina(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIUsuariosPaginacion entidad) {
        int respuesta = -1;
        try{
            respuesta = usuarioDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIUsuarioRolUtil AutenticarUsuario(SRIUsuario entidad) {
        SRIUsuarioRolUtil respuesta = null;
        try{
            respuesta = usuarioDao.AutenticarUsuario(entidad);
        }
        catch(Exception ex){
            throw  ex;
        }
        return respuesta;
    }

    
}
