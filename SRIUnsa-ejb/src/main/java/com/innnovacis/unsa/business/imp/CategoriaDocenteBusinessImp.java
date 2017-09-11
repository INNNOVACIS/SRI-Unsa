
package com.innnovacis.unsa.business.imp;

import com.innnovacis.unsa.business.ICategoriaDocenteBusiness;
import com.innnovacis.unsa.dao.ICategoriaDocenteDao;
import com.innnovacis.unsa.model.SRICategoriaDocente;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class CategoriaDocenteBusinessImp implements ICategoriaDocenteBusiness {

    @Inject  
    private ICategoriaDocenteDao categoriaDocenteDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRICategoriaDocente entidad) {
        int id = -1;
        try{
            entidad = categoriaDocenteDao.Insert(entidad);
            id = entidad.getNIdCategoriaDocente();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRICategoriaDocente entidad) {
        boolean respuesta = false;
         try{
            categoriaDocenteDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRICategoriaDocente entidad) {
        boolean respuesta = false;
         try{
            categoriaDocenteDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRICategoriaDocente Get(int idEntidad) {
        SRICategoriaDocente respuesta = null;
         try{
            respuesta = categoriaDocenteDao.GetById(idEntidad);
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public List<SRICategoriaDocente> GetAll() {
         List<SRICategoriaDocente> respuesta = null;
         try{
            respuesta = categoriaDocenteDao.GetAll();
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRICategoriaDocente GetById(int id) {
        SRICategoriaDocente respuesta = null;
        try{
            respuesta = categoriaDocenteDao.GetById(id);
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
            respuesta = categoriaDocenteDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRICategoriaDocente> GetPagina(SRIPaginacionObject object) {
        List<SRICategoriaDocente> respuesta = null;
        try{
            respuesta = categoriaDocenteDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }
}
