
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionRevisadaBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionRevisadaDao;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ActividadInvestigacionRevisadaBusinessImp implements IActividadInvestigacionRevisadaBusiness {

    @Inject
    private IActividadInvestigacionRevisadaDao actividadRevisadaDao;

    @Override
    public List<SRIActividadGeneralPaginacion> GetAll() {
         List<SRIActividadGeneralPaginacion> respuesta = null;
         try{
            respuesta = actividadRevisadaDao.GetAll();
        }
        catch(Exception ex){

        }
         return respuesta;
    }

    @Override
    public List<SRIActividadGeneralPaginacion> GetPaginacion(SRIPaginacion entidad) {
        
        List<SRIActividadGeneralPaginacion> respuesta = null;
         try{
            respuesta = actividadRevisadaDao.GetPagina(entidad);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacion entidad) {
        int respuesta = -1;
        try{
            respuesta = actividadRevisadaDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
            System.out.println("Error =========> " + ex.getMessage());
        }
        return respuesta;
    }

}
