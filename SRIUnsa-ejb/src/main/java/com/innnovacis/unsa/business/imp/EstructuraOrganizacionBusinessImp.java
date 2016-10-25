
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IEstructuraOrganizacionBusiness;
import com.innnovacis.unsa.dao.IEstructuraOrganizacionDao;

import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class EstructuraOrganizacionBusinessImp implements IEstructuraOrganizacionBusiness {

    @Inject
    private IEstructuraOrganizacionDao estructuraOrganizacionDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIEstructuraOrganizacion entidad) {
        int id = -1;
        try{
            entidad = estructuraOrganizacionDao.Insert(entidad);
            id = entidad.getNIdTipoNivel();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIEstructuraOrganizacion entidad) {
        boolean respuesta = false;
         try{
            estructuraOrganizacionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIEstructuraOrganizacion entidad) {
        boolean respuesta = false;
         try{
            estructuraOrganizacionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIEstructuraOrganizacion Get(int idEntidad) {
        SRIEstructuraOrganizacion respuesta = null;
         try{
            respuesta = estructuraOrganizacionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIEstructuraOrganizacion> GetAll() {
 
         List<SRIEstructuraOrganizacion> respuesta = null;
         try{
            respuesta = estructuraOrganizacionDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
