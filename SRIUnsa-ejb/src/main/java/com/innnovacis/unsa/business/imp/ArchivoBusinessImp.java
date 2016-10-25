
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IArchivoBusiness;
import com.innnovacis.unsa.dao.IArchivoDao;
import com.innnovacis.unsa.model.SRIArchivo;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ArchivoBusinessImp implements IArchivoBusiness {

    @Inject
    private IArchivoDao archivoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIArchivo entidad) {
        int id = -1;
        try{
            entidad = archivoDao.Insert(entidad);
            id = entidad.getNIdArchivo();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIArchivo entidad) {
        boolean respuesta = false;
         try{
            archivoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIArchivo entidad) {
        boolean respuesta = false;
         try{
            archivoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIArchivo Get(int idEntidad) {
        SRIArchivo respuesta = null;
         try{
            respuesta = archivoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIArchivo> GetAll() {
         List<SRIArchivo> respuesta = null;
         try{
            respuesta = archivoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
