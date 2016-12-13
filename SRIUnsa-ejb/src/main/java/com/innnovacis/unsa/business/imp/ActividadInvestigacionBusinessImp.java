
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;
import com.innnovacis.unsa.dao.IEstadoDao;
import com.innnovacis.unsa.dao.IFlujoActorDao;
import com.innnovacis.unsa.dao.IFlujoAristaDao;
import com.innnovacis.unsa.dao.IPersonaColaboradorDao;
import com.innnovacis.unsa.dao.IPersonaDao;
import com.innnovacis.unsa.dao.IPlanificacionActividadDao;
import com.innnovacis.unsa.dao.IPlantillaDocumentoActividadDao;
import com.innnovacis.unsa.dao.IProcesoFlujoDao;
import com.innnovacis.unsa.dao.IProcesoFlujoDestinoDao;
import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.dao.IUsuarioFlujoDao;
import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIFlujoArista;
import com.innnovacis.unsa.model.SRIPersona;
import com.innnovacis.unsa.model.SRIPersonaColaborador;
import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import com.innnovacis.unsa.model.SRIPlantillaDocumentoActividad;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;



@Dependent
public class ActividadInvestigacionBusinessImp implements IActividadInvestigacionBusiness {

    @Inject
    private Logger log;
    
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
    private IFlujoActorDao flujoActorDao;
    
    @Inject
    private IPersonaColaboradorDao personaColaboradorDao;
    
    @Inject
    private IPlantillaDocumentoActividadDao plantillaDocumentoActividadDao;
    
    @Inject
    private IPersonaDao personaDao;

    @Override
    public int Insertar(SRIActividadInvestigacion entidad) {
        int id = -1;
        try{
            entidad = actividadInvestigacionDao.Insert(entidad);
            id = entidad.getNIdActividadInvestigacion();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIActividadGeneral entidad) {
        boolean respuesta = false;
        SRIActividadInvestigacion actividadInvestigacion = entidad.getActividadInvestigacion();
        List<SRIPersona> persona = entidad.getColaboradores();
        List<SRIPlantillaDocumentoActividad> plantillaDocumentoActividad = entidad.getPlantillaDocumentoActividad();
        try{
            actividadInvestigacionDao.Update(actividadInvestigacion);
            for(int i = 0; i < plantillaDocumentoActividad.size(); i++){
                plantillaDocumentoActividadDao.Update(plantillaDocumentoActividad.get(i));
            }
//            for(int i = 0; i < persona.size(); i++){
//                personaDao.Update(persona.get(i));
//            }
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
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
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIActividadGeneral Get(int idEntidad) {
        SRIActividadGeneral respuesta = new SRIActividadGeneral();
        SRIActividadInvestigacion actividadInvestigacion = null;
        List<SRIPersona> persona = null;
        List<SRIPlantillaDocumentoActividad> plantillaDocumentoActividad = null;
        try{
            actividadInvestigacion = actividadInvestigacionDao.GetById(idEntidad);
            persona = personaDao.GetPersonasByIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            plantillaDocumentoActividad = plantillaDocumentoActividadDao.GetPlantillaDocumentoActividadByIdActividad(actividadInvestigacion.getNIdActividadInvestigacion());
            
            respuesta.setPlantillaDocumentoActividad(plantillaDocumentoActividad);
            respuesta.setColaboradores(persona);
            respuesta.setActividadInvestigacion(actividadInvestigacion);
        }
        catch(Exception ex){
            throw ex;
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
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujoOrigen = -1;
        int idUsuarioFlujoDestino = -1;
        SRIUsuario usuarioOrigen = null;
        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        
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
         
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            usuariosDestino = usuarioDao.GetByIdActorDestino(flujoArista.getSIdFlujoActorDestino());
            
            procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            procesoFlujo.setSEstado("A");
            procesoFlujo = procesoFlujoDao.Insert(procesoFlujo);
            
            actividadInvestigacion = actividadInvestigacionDao.Insert(entidad.getActividadInvestigacion());
            planificacionActividad.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            planificacionActividad = planificacionActividadDao.Insert(planificacionActividad);
            
            detalleInvestigacionFlujo.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
            detalleInvestigacionFlujo.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            detalleInvestigacionFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            detalleInvestigacionFlujo.setSEstado("A");
            detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.Insert(detalleInvestigacionFlujo);
            
            entidad.setIdPlanificacion(planificacionActividad.getNIdPlanificacionActividad());
            entidad.getActividadInvestigacion().setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            
            /* Insert ProcesoFlujoDestino*/
            for(int i = 0; i < usuariosDestino.size(); i++){
                SRIProcesoFlujoDestino procesoFlujoDestino = new SRIProcesoFlujoDestino();
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
            
            /*Insertar Colaboradores*/
            for(int i = 0; i < entidad.getColaboradores().size(); i++){
                SRIPersonaColaborador personaColaborador = new SRIPersonaColaborador();
                personaColaborador.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
                personaColaborador.setNIdPersona(entidad.getColaboradores().get(i).getNIdPersona());
                personaColaborador.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                personaColaborador.setSEstado("A");
                personaColaborador = personaColaboradorDao.Insert(personaColaborador);
            }
            
            /*Insertar PlantillaDocumentoActividades*/
            for(int i = 0; i < entidad.getPlantillaDocumentoActividad().size(); i++){
                SRIPlantillaDocumentoActividad plantillaDocumentoActividad = new SRIPlantillaDocumentoActividad();
                plantillaDocumentoActividad.setNIdPlantillaDocumento(entidad.getPlantillaDocumentoActividad().get(i).getNIdPlantillaDocumento());
                plantillaDocumentoActividad.setNIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
                plantillaDocumentoActividad.setSValor(entidad.getPlantillaDocumentoActividad().get(i).getSValor());
                plantillaDocumentoActividad.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                plantillaDocumentoActividad.setSEstado("A");
                plantillaDocumentoActividad = plantillaDocumentoActividadDao.Insert(plantillaDocumentoActividad);
            }
            
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public SRIActividadGeneral AprobarActividadInvestigacion(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujoOrigen = -1;
        SRIUsuarioFlujo usuarioFlujoOrigen = new SRIUsuarioFlujo();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIUsuario usuarioOrigen = new SRIUsuario();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();
        
        try{
            /*Get o crear UsuarioFlujo*/
            usuarioFlujoOrigen.setNIdFlujoActor(entidad.getIdFlujoActorOrigen());
            usuarioFlujoOrigen.setNIdUsuario(entidad.getIdUsuario());
            idUsuarioFlujoOrigen = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoOrigen);
            /*Get FlujoArista*/
            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            
            procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            procesoFlujo.setSEstado("A");
            procesoFlujo = procesoFlujoDao.Insert(procesoFlujo);
            
            detalleInvestigacionFlujo.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
            detalleInvestigacionFlujo.setNIdActividadInvestigacion(entidad.getActividadInvestigacion().getNIdActividadInvestigacion());
            detalleInvestigacionFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            detalleInvestigacionFlujo.setSEstado("A");
            detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.Insert(detalleInvestigacionFlujo);
        } catch(Exception ex) {
            throw ex;
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
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public Map<String, Object> GetActividadesPendientes(SRIPaginacion entidad) {
        
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesPendientes(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesPendientes(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> GetActividadesRevisadas(SRIPaginacion entidad) {
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIFlujoActor flujoActor = null; 
        try{
            flujoActor = flujoActorDao.GetById(entidad.getIdFlujoActor());
            entidad.setCodigoActor(flujoActor.getSCodigo());
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesRevisadas(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesRevisadas(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean EnviarEmail(SRIActividadGeneral entidad) {
        boolean respuesta = false;
        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIActividadInvestigacion actividadInvestigacion = null;
        try {
            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
            usuariosDestino = usuarioDao.GetByIdActorDestino(flujoArista.getSIdFlujoActorDestino());
            actividadInvestigacion =  actividadInvestigacionDao.GetById(entidad.getActividadInvestigacion().getNIdActividadInvestigacion());
            Email email = new Email();
            List<String> to = new ArrayList<String>();
            for(int i = 0 ; i < usuariosDestino.size(); i++){
                to.add(usuariosDestino.get(i).getSUsuarioEmail());
            }
            
            log.log(Level.INFO, "Email enable : {0}", email.recuperar());
            log.log(Level.INFO, "Email enable : {0}", to);
            email.initGmail(to,actividadInvestigacion);
            
            
            respuesta = true;
        } catch(Exception ex) {
            respuesta = false;
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(ActividadInvestigacionBusinessImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> GetActividadesByDocente(SRIPaginacion entidad) {
        
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesByDocente(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesByDocente(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> GetActividadesByDocenteDetalle(SRIPaginacion entidad) {
        
        int total = -1;
        List<SRIActividadInvestigacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesByDocenteDetalle(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesByDocenteDetalle(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

}
