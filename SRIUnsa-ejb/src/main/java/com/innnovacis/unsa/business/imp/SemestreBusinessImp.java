
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.ISemestreBusiness;
import com.innnovacis.unsa.business.ITipoNivelBusiness;
import com.innnovacis.unsa.dao.ISemestreDao;
import com.innnovacis.unsa.dao.ITipoNivelDao;
import com.innnovacis.unsa.model.SRISemestre;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class SemestreBusinessImp implements ISemestreBusiness {

    @Inject
    private ISemestreDao semestreDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRISemestre entidad) {
        int id = -1;
        try{
            entidad = semestreDao.Insert(entidad);
            id = entidad.getNIdSemestre();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRISemestre entidad) {
        boolean respuesta = false;
         try{
            semestreDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRISemestre entidad) {
        boolean respuesta = false;
         try{
            semestreDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRISemestre Get(int idEntidad) {
        SRISemestre respuesta = null;
         try{
            respuesta = semestreDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRISemestre> GetAll() {

         List<SRISemestre> respuesta = null;
         try{
            respuesta = semestreDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
