
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.dao.ITipoInvestigadorDao;
import com.innnovacis.unsa.model.SRITipoInvestigador;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import com.innnovacis.unsa.business.ITipoInvestigadorBusiness;
import com.innnovacis.unsa.util.SRIPaginacionObject;



@Dependent
public class TipoInvestigadorBusinessImp implements ITipoInvestigadorBusiness {

    @Inject
    private ITipoInvestigadorDao tipoInvestigadorDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRITipoInvestigador entidad) {
        int id = -1;
        try{
            entidad = tipoInvestigadorDao.Insert(entidad);
            id = entidad.getNIdTipoInvestigador();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRITipoInvestigador entidad) {
        boolean respuesta = false;
         try{
            tipoInvestigadorDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRITipoInvestigador entidad) {
        boolean respuesta = false;
         try{
            tipoInvestigadorDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRITipoInvestigador Get(int idEntidad) {
        SRITipoInvestigador respuesta = null;
         try{
            respuesta = tipoInvestigadorDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoInvestigador> GetAll() {
         List<SRITipoInvestigador> respuesta = null;
         try{
            respuesta = tipoInvestigadorDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        int respuesta = -1;
         try{
            respuesta = tipoInvestigadorDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRITipoInvestigador> GetPagina(SRIPaginacionObject entidad) {
        List<SRITipoInvestigador> respuesta = null;
         try{
            respuesta = tipoInvestigadorDao.GetPagina(entidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    
}
