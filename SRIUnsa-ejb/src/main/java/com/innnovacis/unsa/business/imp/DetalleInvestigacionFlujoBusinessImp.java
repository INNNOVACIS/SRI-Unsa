
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IDetalleInvestigacionFlujoBusiness;
import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class DetalleInvestigacionFlujoBusinessImp implements IDetalleInvestigacionFlujoBusiness {

    @Inject
    private IDetalleInvestigacionFlujoDao detalleInvestigacionFlujoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIDetalleInvestigacionFlujo entidad) {
        int id = -1;
        try{
            entidad = detalleInvestigacionFlujoDao.Insert(entidad);
            id = entidad.getNIdDetalleInvestigacionFlujo();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIDetalleInvestigacionFlujo entidad) {
        boolean respuesta = false;
         try{
            detalleInvestigacionFlujoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIDetalleInvestigacionFlujo entidad) {
        boolean respuesta = false;
         try{
            detalleInvestigacionFlujoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIDetalleInvestigacionFlujo Get(int idEntidad) {
        SRIDetalleInvestigacionFlujo respuesta = null;
         try{
            respuesta = detalleInvestigacionFlujoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIDetalleInvestigacionFlujo> GetAll() {
         List<SRIDetalleInvestigacionFlujo> respuesta = null;
         try{
            respuesta = detalleInvestigacionFlujoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
