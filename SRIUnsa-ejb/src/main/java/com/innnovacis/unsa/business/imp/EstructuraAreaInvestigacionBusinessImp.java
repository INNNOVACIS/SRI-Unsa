
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IEstructuraAreaInvestigacionBusiness;
import com.innnovacis.unsa.dao.IEstructuraAreaInvestigacionDao;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class EstructuraAreaInvestigacionBusinessImp implements IEstructuraAreaInvestigacionBusiness {

    @Inject
    private IEstructuraAreaInvestigacionDao estructuraAreaInvestigacionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIEstructuraAreaInvestigacion entidad) {
        int id = -1;
        try{
            entidad = estructuraAreaInvestigacionDao.Insert(entidad);
            id = entidad.getNIdEstructura();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIEstructuraAreaInvestigacion entidad) {
        boolean respuesta = false;
         try{
            estructuraAreaInvestigacionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIEstructuraAreaInvestigacion entidad) {
        boolean respuesta = false;
         try{
            estructuraAreaInvestigacionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIEstructuraAreaInvestigacion Get(int idEntidad) {
        SRIEstructuraAreaInvestigacion respuesta = null;
         try{
            respuesta = estructuraAreaInvestigacionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIEstructuraAreaInvestigacion> GetAll() {
         List<SRIEstructuraAreaInvestigacion> respuesta = null;
         try{
            respuesta = estructuraAreaInvestigacionDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject object) {
        int respuesta = -1;
         try{
            respuesta = estructuraAreaInvestigacionDao.GetTotalPaginacion(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIEstructuraAreaInvestigacion> GetPagina(SRIPaginacionObject object) {
        List<SRIEstructuraAreaInvestigacion> respuesta = null;
         try{
            respuesta = estructuraAreaInvestigacionDao.GetPagina(object);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
