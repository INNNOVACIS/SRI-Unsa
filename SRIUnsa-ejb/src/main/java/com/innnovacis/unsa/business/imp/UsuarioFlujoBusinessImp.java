
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IUsuarioFlujoBusiness;
import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;


import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class UsuarioFlujoBusinessImp implements IUsuarioFlujoBusiness {

    @Inject
    private IUsuarioFlujoDao usuarioFlujoDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIUsuarioFlujo entidad) {
        int id = -1;
        try{
            entidad = usuarioFlujoDao.Insert(entidad);
            id = entidad.getNIdUsuarioFlujo();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIUsuarioFlujo entidad) {
        boolean respuesta = false;
         try{
            usuarioFlujoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIUsuarioFlujo entidad) {
        boolean respuesta = false;
         try{
            usuarioFlujoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIUsuarioFlujo Get(int idEntidad) {
        SRIUsuarioFlujo respuesta = null;
         try{
            respuesta = usuarioFlujoDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuarioFlujo> GetAll() {
         List<SRIUsuarioFlujo> respuesta = null;
         try{
            respuesta = usuarioFlujoDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
