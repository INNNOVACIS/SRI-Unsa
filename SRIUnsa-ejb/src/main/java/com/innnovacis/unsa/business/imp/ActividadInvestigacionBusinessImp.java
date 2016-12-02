
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;
import com.innnovacis.unsa.dao.IEstadoDao;
import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.dao.IProcesoFlujoDestinoDao;
import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import com.innnovacis.unsa.model.SRIEstado;
import com.innnovacis.unsa.model.SRIFlujoArista;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import com.innnovacis.unsa.model.SRIProcesoFlujo;
import com.innnovacis.unsa.model.SRIProcesoFlujoDestino;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.Email;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
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
    private IProcesoFlujoDestinoDao procesoFlujoDestinoDao;
    
    @Inject
    private IEstadoDao estadoDao;
    
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
    public Map<String, Object> GetActividadPaginacion(SRIPaginacion entidad) {
        
        Map<String, Object> respuestaMap = new HashMap<>();
        int total = -1;
        int idUsuarioFlujo = -1;
        SRIEstado estado = null;
        String stringIdProcesoFlujo = "";
        String stringIDActividades = "";
        
        List<SRIActividadGeneralPaginacion> respuesta = null;
        List<SRIProcesoFlujoDestino> lstProcesoFlujoDestino = null;
        SRIUsuarioFlujo usuarioFlujo = new SRIUsuarioFlujo();
        List<SRIDetalleInvestigacionFlujo> lstDetalleInvestigacionFlujo = null;
        
        try{
            estado = estadoDao.GetById(entidad.getIdEstado());
            
            usuarioFlujo.setNIdFlujoActor(entidad.getIdFlujoActor());
            usuarioFlujo.setNIdUsuario(entidad.getIdUsuario());
            idUsuarioFlujo = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujo);
            
            lstProcesoFlujoDestino = procesoFlujoDestinoDao.GetByIdUsuarioFlujoAndEstadoEnvio(idUsuarioFlujo, estado.getSNombreEstado());
            for(int i = 0; i < lstProcesoFlujoDestino.size(); i++){
                stringIdProcesoFlujo = stringIdProcesoFlujo + String.valueOf(lstProcesoFlujoDestino.get(i).getNIdProcesoFlujo()) + ",";
            }
            lstDetalleInvestigacionFlujo = detalleInvestigacionFlujoDao.GetByIdProcesoFlujo(stringIdProcesoFlujo);
            
            for(int i = 0; i < lstDetalleInvestigacionFlujo.size(); i++){
                stringIDActividades = stringIDActividades + String.valueOf(lstDetalleInvestigacionFlujo.get(i).getNIdActividadInvestigacion()) + ",";
            }
            respuesta = actividadInvestigacionDao.GetPagina(entidad, stringIDActividades);
            total = actividadInvestigacionDao.GetTotalPagina(entidad, stringIDActividades);
            
            respuestaMap.put("total", total);
            respuestaMap.put("lista", respuesta);
            
        } catch(Exception ex) {
        
        }
        return respuestaMap;
    }
    
    @Override
    public SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujoOrigen = -1;
        int idUsuarioFlujoDestino = -1;
        SRIUsuario usuarioOrigen = null;
        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        SRIProcesoFlujoDestino procesoFlujoDestino = new SRIProcesoFlujoDestino();
        SRIActividadInvestigacion actividadInvestigacion = new SRIActividadInvestigacion();
        SRIPlanificacionActividad planificacionActividad = new SRIPlanificacionActividad(); 
        SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();
        
        SRIUsuarioFlujo usuarioFlujoOrigen = new SRIUsuarioFlujo();
        SRIUsuarioFlujo usuarioFlujoDestino = new SRIUsuarioFlujo();
        
        try{
            usuarioFlujoOrigen.setNIdFlujoActor(entidad.getIdFlujoActorOrigen());
            usuarioFlujoOrigen.setNIdUsuario(entidad.getIdUsuario());
            
            idUsuarioFlujoOrigen = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoOrigen);
            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
         
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
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
            
            /* Insert ProcesoFlujoDestino*/
            for(int i = 0; i < usuariosDestino.size(); i++){
                usuarioFlujoDestino.setNIdFlujoActor(flujoArista.getSIdFlujoActorDestino());
                usuarioFlujoDestino.setNIdUsuario(usuariosDestino.get(i).getNIdUsuario());
                idUsuarioFlujoDestino = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoDestino);
                
                procesoFlujoDestino.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
                procesoFlujoDestino.setNIdUsuarioFlujo(idUsuarioFlujoDestino);
                procesoFlujoDestino.setSEstadoEnvio("CREADO");
                procesoFlujoDestino.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                procesoFlujoDestino.setSEstado("A");
                procesoFlujoDestino = procesoFlujoDestinoDao.Insert(procesoFlujoDestino);
            }
            
            Email email = new Email();
            List<String> to = new ArrayList<String>();
            for(int i = 0 ; i < usuariosDestino.size(); i++){
                to.add(usuariosDestino.get(i).getSUsuarioEmail());
            }
            to.add(usuarioOrigen.getSUsuarioEmail());
            email.initGmail(to,entidad.getActividadInvestigacion());
        } catch(Exception ex) {
            
        }
        return entidad;
    }

    @Override
    public SRIActividadGeneral AprobarActividadInvestigacion(SRIActividadGeneral entidad) {
        
        SRIActividadGeneral respuesta= null;
        int idUsuarioFlujoOrigen = -1;
        SRIUsuario usuarioOrigen = null;
        SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  null;
        SRIUsuarioFlujo usuarioFlujoOrigen = new SRIUsuarioFlujo();
        
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        
        try{
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.GetByIdActividad(entidad.getActividadInvestigacion().getNIdActividadInvestigacion());
            usuarioFlujoOrigen.setNIdFlujoActor(entidad.getIdFlujoActorOrigen());
            usuarioFlujoOrigen.setNIdUsuario(entidad.getIdUsuario());
            
            idUsuarioFlujoOrigen = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoOrigen);
            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
            
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo = procesoFlujoDao.Insert(procesoFlujo);
            
            /*UpdateDetalle con nuevo idProcesoFlujo*/
            detalleInvestigacionFlujo.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
            detalleInvestigacionFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
            detalleInvestigacionFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
            detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.Update(detalleInvestigacionFlujo);
//            if(detalleInvestigacionFlujo.getNIdDetalleInvestigacionFlujo() != 0){
//            
//            }
                
        } catch(Exception ex) {
        
        }
        return entidad;
    }

    @Override
    public Map<String, Object> GetActividadesGeneradas(SRIPaginacion entidad) {
        
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesGeneradas(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesGeneradas(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
        
        }
        return respuesta;
    }

}
