
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.ITipoAsesoriaBusiness;
import com.innnovacis.unsa.dao.ITipoAsesoriaDao;
import com.innnovacis.unsa.model.SRITipoAsesoria;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class TipoAsesoriaBusinessImp implements ITipoAsesoriaBusiness {

    @Inject
    private ITipoAsesoriaDao tipoAsesoriaDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRITipoAsesoria entidad) {
        int id = -1;
        try{
            entidad = tipoAsesoriaDao.Insert(entidad);
            id = entidad.getNIdTipoAsesoria();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRITipoAsesoria entidad) {
        boolean respuesta = false;
         try{
            tipoAsesoriaDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRITipoAsesoria entidad) {
        boolean respuesta = false;
         try{
            tipoAsesoriaDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRITipoAsesoria Get(int idEntidad) {
        SRITipoAsesoria respuesta = null;
         try{
            respuesta = tipoAsesoriaDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoAsesoria> GetAll() {
         List<SRITipoAsesoria> respuesta = null;
         try{
            respuesta = tipoAsesoriaDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
         try{
            respuesta = tipoAsesoriaDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoAsesoria> GetPagina(SRIPaginacionObject object) {
         List<SRITipoAsesoria> respuesta = null;
         try{
            respuesta = tipoAsesoriaDao.GetPagina(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
