
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;
import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import com.innnovacis.unsa.model.SRIFlujoArista;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.Email;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import java.util.ArrayList;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ActividadInvestigacionBusinessImp implements IActividadInvestigacionBusiness {

    @Inject
    private IActividadInvestigacionDao actividadInvestigacionDao;
    
    @Inject
    private IProcesoFlujoDao procesoFlujoDao;
    
    @Inject
    private IDetalleInvestigacionFlujoDao detalleInvestigacionFlujoDao;
    
    @Inject
    private IFlujoAristaDao flujoAristaDao;
    
    @Inject
    private IPlanificacionActividadDao planificacionActividadDao;
    
    @Inject
    private IUsuarioFlujoDao usuarioFlujoDao;
    
    @Inject
    private IUsuarioDao usuarioDao;
    
    @Inject
    private Logger log;

    @Override
    public int Insertar(SRIActividadInvestigacion entidad) {
        int id = -1;
        try{
            entidad = actividadInvestigacionDao.Insert(entidad);
            id = entidad.getNIdActividadInvestigacion();
                    
        }
        catch(Exception ex){
            
        }
        return id;
    }

    @Override
    public boolean Update(SRIActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            actividadInvestigacionDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIActividadInvestigacion entidad) {
        boolean respuesta = false;
         try{
            actividadInvestigacionDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            
        }
         return respuesta;
    }

    @Override
    public SRIActividadInvestigacion Get(int idEntidad) {
        SRIActividadInvestigacion respuesta = null;
         try{
            respuesta = actividadInvestigacionDao.GetById(idEntidad);
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public List<SRIActividadInvestigacion> GetAll() {
         List<SRIActividadInvestigacion> respuesta = null;
         try{
            respuesta = actividadInvestigacionDao.GetAll();
        }
        catch(Exception ex){
        }
         return respuesta;
    }

    @Override
    public SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujo = -1;
        SRIUsuario usuarioOrigen = null;
        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        SRIActividadInvestigacion actividadInvestigacion = new SRIActividadInvestigacion();
        SRIPlanificacionActividad planificacionActividad = new SRIPlanificacionActividad(); 
        SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();
        
        SRIUsuarioFlujo usuarioFlujo = new SRIUsuarioFlujo();
        usuarioFlujo.setNIdFlujoActor(entidad.getIdFlujoActorOrigen());
        usuarioFlujo.setNIdUsuario(entidad.getIdUsuario());
        
        try{
            idUsuarioFlujo = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujo);
            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
         
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujo);
            procesoFlujo = procesoFlujoDao.Insert(procesoFlujo);
            
            actividadInvestigacion = actividadInvestigacionDao.Insert(entidad.getActividadInvestigacion());
            planificacionActividad.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            planificacionActividad = planificacionActividadDao.Insert(planificacionActividad);
            
            detalleInvestigacionFlujo.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
            detalleInvestigacionFlujo.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            detalleInvestigacionFlujo.setSUserCreacion("Administrador");
            detalleInvestigacionFlujo.setSEstado("A");
            detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.Insert(detalleInvestigacionFlujo);
            
            entidad.setIdPlanificacion(planificacionActividad.getNIdPlanificacionActividad());
            
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            usuariosDestino = usuarioDao.GetByIdActorDestino(flujoArista.getSIdFlujoActorDestino());
            Email email = new Email();
            List<String> to = new ArrayList<String>();
            for(int i = 0 ; i < usuariosDestino.size(); i++){
                to.add(usuariosDestino.get(i).getSUsuarioEmail());
            }
            to.add(usuarioOrigen.getSUsuarioEmail());
            email.initGmail(to,entidad.getActividadInvestigacion());
        }
        catch(Exception ex){
        }
        return entidad;
    }
}
