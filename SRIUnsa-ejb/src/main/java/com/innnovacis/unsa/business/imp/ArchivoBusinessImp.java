
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IArchivoBusiness;
import com.innnovacis.unsa.dao.IArchivoDao;
import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;



@RequestScoped
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
    public List<SRIArchivoUtil> GetAll() {
         List<SRIArchivoUtil> respuesta = null;
        try{
            respuesta = archivoDao.GetAll();
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public Response descargarArchivo(int id) {
        Response respuesta = null;
        try{
            respuesta = archivoDao.descargarArchivo(id);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public List<SRIArchivoUtil> GetArchivosById(int id) {
        List<SRIArchivoUtil> respuesta = null;
        try{
            respuesta = archivoDao.GetArchivosById(id);
        }
        catch(Exception ex){
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
         try{
            respuesta = archivoDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public List<SRIArchivoUtil> GetPagina(SRIPaginacionObject object) {
        List<SRIArchivoUtil> respuesta = null;
         try{
            respuesta = archivoDao.GetPagina(object);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

}
