
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IPlantillaDocumentoBusiness;
import com.innnovacis.unsa.dao.IPlantillaDocumentoDao;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.model.SRIPlantillaDocumento;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.inject.Inject;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;

@Dependent
public class PlantillaDocumentoBusinessImp implements IPlantillaDocumentoBusiness {

    @Inject
    private IPlantillaDocumentoDao plantillaDocumentoDao;
    
    @Inject
    private Logger log;
    
    @Override
    public int Insertar(SRIPlantillaDocumento entidad) {
        int id = -1;
        try{
            entidad = plantillaDocumentoDao.Insert(entidad);
            id = entidad.getNIdPlantillaDocumento(); 
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIPlantillaDocumento entidad) {
        boolean respuesta = false;
         try{
            plantillaDocumentoDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
        return respuesta;
    }

    @Override
    public boolean Delete(SRIPlantillaDocumento entidad) {
        boolean respuesta = false;
         try{
            plantillaDocumentoDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        int respuesta = -1;
        try{
            respuesta = plantillaDocumentoDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIPlantillaDocumento> GetPagina(SRIPaginacionObject entidad) {
        List<SRIPlantillaDocumento> respuesta = null;
         try{
            respuesta = plantillaDocumentoDao.GetPagina(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIPlantillaDocumento> GetPlantillaDocumentoByFacultad(SRIEstructuraOrganizacion entidad) {
        List<SRIPlantillaDocumento> respuesta = null;
         try{
            respuesta = plantillaDocumentoDao.GetPlantillaDocumentoByFacultad(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

}
