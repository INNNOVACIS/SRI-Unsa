
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionRevisadaBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionRevisadaDao;
import com.innnovacis.unsa.util.SRIActividadesRevisadas;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ActividadInvestigacionRevisadaBusinessImp implements IActividadInvestigacionRevisadaBusiness {

    @Inject
    private IActividadInvestigacionRevisadaDao actividadRevisadaDao;

    @Override
    public List<SRIActividadesRevisadas> GetAll() {
         List<SRIActividadesRevisadas> respuesta = null;
         try{
            respuesta = actividadRevisadaDao.GetAll();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
         return respuesta;
    }

}
