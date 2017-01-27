
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.ITipoNivelBusiness;
import com.innnovacis.unsa.dao.ITipoNivelDao;
import com.innnovacis.unsa.model.SRITipoNivel;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class TipoNivelBusinessImp implements ITipoNivelBusiness {

    @Inject
    private ITipoNivelDao tipoNivelDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRITipoNivel entidad) {
        int id = -1;
        try{
            entidad = tipoNivelDao.Insert(entidad);
            id = entidad.getNIdTipoNivel();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRITipoNivel entidad) {
        boolean respuesta = false;
         try{
            tipoNivelDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRITipoNivel entidad) {
        boolean respuesta = false;
         try{
            tipoNivelDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRITipoNivel Get(int idEntidad) {
        SRITipoNivel respuesta = null;
         try{
            respuesta = tipoNivelDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoNivel> GetAll() {
         List<SRITipoNivel> respuesta = null;
         try{
            respuesta = tipoNivelDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
        try{
            respuesta = tipoNivelDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRITipoNivel> GetPagina(SRIPaginacionObject object) {
        List<SRITipoNivel> respuesta = null;
        try{
            respuesta = tipoNivelDao.GetPagina(object);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    
}
