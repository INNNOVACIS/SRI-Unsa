
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.business.IFlujoAristaBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.IEstructuraAreaInvestigacionDao;
import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import com.innnovacis.unsa.model.SRIFlujoArista;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class FlujoAristaBusinessImp implements IFlujoAristaBusiness {

    @Inject
    private IFlujoAristaDao flujoAristaDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIFlujoArista entidad) {
        int id = -1;
        try{
            entidad = flujoAristaDao.Insert(entidad);
            id = entidad.getNIdArista();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIFlujoArista entidad) {
        boolean respuesta = false;
         try{
            flujoAristaDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }
// pruebaa
    @Override
    public boolean Delete(SRIFlujoArista entidad) {
        boolean respuesta = false;
         try{
            flujoAristaDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIFlujoArista Get(int idEntidad) {
        SRIFlujoArista respuesta = null;
         try{
            respuesta = flujoAristaDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIFlujoArista> GetAll() {
         List<SRIFlujoArista> respuesta = null;
         try{
            respuesta = flujoAristaDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
