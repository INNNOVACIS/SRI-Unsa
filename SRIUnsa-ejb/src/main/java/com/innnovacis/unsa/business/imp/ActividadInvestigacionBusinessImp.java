
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IActividadInvestigacionBusiness;
import com.innnovacis.unsa.dao.IActividadInvestigacionDao;
import com.innnovacis.unsa.dao.ICabeceraMasivaDao;
import com.innnovacis.unsa.dao.IDetalleInvestigacionFlujoDao;
import com.innnovacis.unsa.dao.IDetalleMasivaDao;
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
import com.innnovacis.unsa.model.SRICabeceraMasiva;
import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import com.innnovacis.unsa.model.SRIDetalleMasiva;
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
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocentesActividades;
import com.innnovacis.unsa.util.SRIDocentesActivosInactivosFacultad;
import com.innnovacis.unsa.util.SRIEnviarInformeDepartamento;
import com.innnovacis.unsa.util.SRIFlujoActorUtil;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRITotalTipoActividad;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;


@RequestScoped
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
    
    @Inject
    private ICabeceraMasivaDao cabeceraMasivaDao;
    
    @Inject
    private IDetalleMasivaDao detalleMasivaDao;

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
        List<SRIPersona> persona = new ArrayList<SRIPersona>();
        SRIPersona responsable = null;
        SRIPersona director = null;
        SRIPlanificacionActividad planificacion = null;
        List<SRIPlantillaDocumentoActividad> plantillaDocumentoActividad = null;
        try{
            actividadInvestigacion = actividadInvestigacionDao.GetById(idEntidad);
            planificacion = planificacionActividadDao.GetByIdActividad(idEntidad);
//            persona = personaDao.GetPersonasByIdActividadInvestigacion(actividadInvestigacion.getNIdActividadInvestigacion());
            responsable = personaDao.GetById(actividadInvestigacion.getNIdResponsable());
            director = personaDao.GetById(actividadInvestigacion.getNIdDirector());
            plantillaDocumentoActividad = plantillaDocumentoActividadDao.GetPlantillaDocumentoActividadByIdActividad(actividadInvestigacion.getNIdActividadInvestigacion());
            
            respuesta.setPlantillaDocumentoActividad(plantillaDocumentoActividad);
            persona.add(director);
            persona.add(responsable);
            respuesta.setColaboradores(persona);
            respuesta.setActividadInvestigacion(actividadInvestigacion);
            respuesta.setIdPlanificacion(planificacion.getNIdPlanificacionActividad());
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
    public SRIActividadGeneral GuardarInvestigacion(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujoOrigen = -1;
        int idUsuarioFlujoDestino = -1;
        SRIUsuario usuarioOrigen = null;
//        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        List<SRIUsuarioPersona> usuariosPersonaDestino = new ArrayList<SRIUsuarioPersona>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIFlujoActor flujoActor = new SRIFlujoActor();
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
            flujoActor = flujoActorDao.GetById(flujoArista.getSIdFlujoActorDestino());
         
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            usuariosPersonaDestino = usuarioDao.GetDestinatariosByCodigoActorDestino(flujoActor.getSCodigo());
            
            procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            procesoFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
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
            for(int i = 0; i < usuariosPersonaDestino.size(); i++){
                SRIProcesoFlujoDestino procesoFlujoDestino = new SRIProcesoFlujoDestino();
                usuarioFlujoDestino.setNIdFlujoActor(flujoArista.getSIdFlujoActorDestino());
                int id = usuariosPersonaDestino.get(i).getNIdUsuario();
                usuarioFlujoDestino.setNIdUsuario(id);
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
    public SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad) {
        
        int idUsuarioFlujoOrigen = -1;
        int idUsuarioFlujoDestino = -1;
        SRIUsuario usuarioOrigen = null;
//        List<SRIUsuario> usuariosDestino = new ArrayList<SRIUsuario>();
        List<SRIUsuarioPersona> usuariosPersonaDestino = new ArrayList<SRIUsuarioPersona>();
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIFlujoActor flujoActor = new SRIFlujoActor();
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
            flujoActor = flujoActorDao.GetById(flujoArista.getSIdFlujoActorDestino());
         
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            usuariosPersonaDestino = usuarioDao.GetDestinatariosByCodigoActorDestino(flujoActor.getSCodigo());
            
            procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
            procesoFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
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
            for(int i = 0; i < usuariosPersonaDestino.size(); i++){
                SRIProcesoFlujoDestino procesoFlujoDestino = new SRIProcesoFlujoDestino();
                usuarioFlujoDestino.setNIdFlujoActor(flujoArista.getSIdFlujoActorDestino());
                int id = usuariosPersonaDestino.get(i).getNIdUsuario();
                usuarioFlujoDestino.setNIdUsuario(id);
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
        int idFlujoActorOrigen = -1;
        SRIFlujoArista flujoArista = new SRIFlujoArista();
        SRIUsuario usuarioOrigen = new SRIUsuario();
        SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
        SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();
        
        try{
            /*Get o crear UsuarioFlujo*/
//            usuarioFlujoOrigen.setNIdFlujoActor(entidad.getIdFlujoActorOrigen());
//            usuarioFlujoOrigen.setNIdUsuario(entidad.getIdUsuario());
//            idUsuarioFlujoOrigen = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoOrigen);
            
            List<SRIFlujoActorUtil> lstUsuarioFlujoOrigen = usuarioFlujoDao.getUsuarioFlujoActorByIdUsuario(entidad.getIdUsuario());
            idUsuarioFlujoOrigen = GetUsuarioFlujoOrigen(lstUsuarioFlujoOrigen);
            idFlujoActorOrigen = GetIdFujoActorOrigen(lstUsuarioFlujoOrigen);
            
            /*Get FlujoArista*/
//            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
            flujoArista = flujoAristaDao.GetFlujoAristaByIdActorOrigen(idFlujoActorOrigen);
            usuarioOrigen = usuarioDao.GetById(entidad.getIdUsuario());
            
            procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
            procesoFlujo.setNIdArista(flujoArista.getNIdArista());
            procesoFlujo.setSFlujo(flujoArista.getSFlujo());
            procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
            procesoFlujo.setSUserCreacion(entidad.getActividadInvestigacion().getSUserCreacion());
            procesoFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
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
    public Map<String, Object> GetActividadesGeneradasHomeDocente(SRIPaginacion entidad) {
        
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesGeneradasHomeDocente(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesGeneradasHomeDocente(entidad);
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
    public Map<String, Object> GetActividadesRevisadasMasivas(SRIPaginacion entidad) {
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        SRIFlujoActor flujoActor = null; 
        try{
            flujoActor = flujoActorDao.GetById(entidad.getIdFlujoActor());
            entidad.setCodigoActor(flujoActor.getSCodigo());
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesRevisadasMasivas(entidad);
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
//        List<SRIUsuarioPersona> usuariosPersonaDestino   = new ArrayList<SRIUsuarioPersona>();
//        List<SRIUsuarioPersona> usuarioPersonaOrigen     = new ArrayList<SRIUsuarioPersona>();
        SRIActividadInvestigacion actividadInvestigacion = null;
//        SRIFlujoArista flujoArista = new SRIFlujoArista();
//        SRIFlujoActor flujoActor   = null;         
        
        List<String> nombreDestinatarios = new ArrayList<String>();
        List<String> emailDestinatarios = new ArrayList<String>();
        
        try {
            
//            flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.getIdFlujoActorOrigen(), entidad.getIdEstado());
//            flujoActor  = flujoActorDao.GetById(flujoArista.getSIdFlujoActorDestino());
//            usuarioPersonaOrigen   = usuarioDao.GetUsuarioPersonaByIdUsuario(entidad.getIdUsuario());
//            usuariosPersonaDestino = usuarioDao.GetDestinatariosByCodigoActorDestino(flujoActor.getSCodigo());
            actividadInvestigacion =  actividadInvestigacionDao.GetById(entidad.getActividadInvestigacion().getNIdActividadInvestigacion());
            
            
//            Email email = new Email();
//            log.log(Level.INFO, "Email enable : {0}", email.recuperar());
            
//            switch(entidad.getCodigoActor()){
//                case "DOCE":
//                    email.initGmail(usuarioPersonaOrigen, actividadInvestigacion, entidad.getCodigoActor());
//                    break;
//                case "DIDE":
//                    break;
//                case "DIUN":
//                    break;
//                case "DECA":
//                    break;
//                case "DIGE":
//                    break;
//
//            }

            //Get Remitente y destinatarios
            List<SRIUsuarioPersona> lstUsuarioPersonas = usuarioDao.GetDirectorDepartamentoByIdDocente(entidad.getIdUsuario());
            List<SRIUsuarioPersona> remitente = usuarioDao.GetUsuarioPersonaByIdUsuario(entidad.getIdUsuario());
            
            for(SRIUsuarioPersona usuarioPersona : lstUsuarioPersonas){
                nombreDestinatarios.add(usuarioPersona.getSNombre() + " " + usuarioPersona.getSApellido());
                emailDestinatarios.add(usuarioPersona.getSUsuarioEmail());
            }
            String nombreRemitente = remitente.get(0).getSNombre() + " " + remitente.get(0).getSApellido();
            String emailRemitente = remitente.get(0).getSUsuarioEmail();
            String cuerpo = "El registro de su actividad de investigación fue realizada con éxito, pendiente de aprobación del Director de Departamento ";
            String asunto = "SRI-UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String titulo = "Registro de la Actividad de Investigación";
            
            
            Email email = new Email();
            log.log(Level.INFO, "Email enable : {0}", email.recuperar());
             
            email.enviarRegistro(nombreDestinatarios, nombreRemitente, cuerpo, emailDestinatarios, emailRemitente, titulo, asunto, null, actividadInvestigacion);
            
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
    public Map<String, Object> GetActividadesByDocenteColaboradores(SRIPaginacion entidad) {
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        try{
            lstActividadGeneral = actividadInvestigacionDao.GetActividadesByDocenteColaboradores(entidad);
            total = actividadInvestigacionDao.GetTotalActividadesByDocenteColaboradores(entidad);
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

    @Override
    public List<SRIActividadGeneral> AprobarActividadInvestigacionMasivo(List<SRIActividadGeneral> entidad) {
        
        SRICabeceraMasiva cabeceraMasiva = new SRICabeceraMasiva();
        int cabecera = 0;
        for(int i = 0; i < entidad.size(); i++){
            
//            int idUsuarioFlujoOrigen = -1;
//            SRIUsuarioFlujo usuarioFlujoOrigen = new SRIUsuarioFlujo();
//            SRIFlujoArista flujoArista = new SRIFlujoArista();
//            SRIUsuario usuarioOrigen = new SRIUsuario();
//            SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
//            SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();
            
            int idUsuarioFlujoOrigen = -1;
            int idFlujoActorOrigen = -1;
            SRIFlujoArista flujoArista = new SRIFlujoArista();
            SRIUsuario usuarioOrigen = new SRIUsuario();
            SRIProcesoFlujo procesoFlujo = new SRIProcesoFlujo();
            SRIDetalleInvestigacionFlujo detalleInvestigacionFlujo =  new SRIDetalleInvestigacionFlujo();

            try{
                /*Get o crear UsuarioFlujo*/
//                usuarioFlujoOrigen.setNIdFlujoActor(entidad.get(i).getIdFlujoActorOrigen());
//                usuarioFlujoOrigen.setNIdUsuario(entidad.get(i).getIdUsuario());
//                idUsuarioFlujoOrigen = usuarioFlujoDao.CreateAndGetUsuarioFlujo(usuarioFlujoOrigen);
                
                
                List<SRIFlujoActorUtil> lstUsuarioFlujoOrigen = usuarioFlujoDao.getUsuarioFlujoActorByIdUsuario(entidad.get(i).getIdUsuario());
                idUsuarioFlujoOrigen = GetUsuarioFlujoOrigen(lstUsuarioFlujoOrigen);
                idFlujoActorOrigen = GetIdFujoActorOrigen(lstUsuarioFlujoOrigen);
                
                /*Get FlujoArista*/
//                flujoArista = flujoAristaDao.GetFlujoAristaByIdOrigenIdEstado(entidad.get(i).getIdFlujoActorOrigen(), entidad.get(i).getIdEstado());
//                usuarioOrigen = usuarioDao.GetById(entidad.get(i).getIdUsuario());
                
                flujoArista = flujoAristaDao.GetFlujoAristaByIdActorOrigen(idFlujoActorOrigen);
                usuarioOrigen = usuarioDao.GetById(entidad.get(i).getIdUsuario());

                /* Insertar CabeceraMasiva */
                if(cabecera == 0){
                    cabeceraMasiva.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
                    cabeceraMasiva.setFlujo(flujoArista.getSFlujo());
                    cabeceraMasiva.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                    cabeceraMasiva.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
                    cabeceraMasiva.setSEstado("A");
                    cabeceraMasiva = cabeceraMasivaDao.Insert(cabeceraMasiva);
                    cabecera = 1;
                }
                
                procesoFlujo.setNIdEstado(flujoArista.getNIdEstado());
                procesoFlujo.setNIdArista(flujoArista.getNIdArista());
                procesoFlujo.setSFlujo(flujoArista.getSFlujo());
                procesoFlujo.setNIdUsuarioFlujo(idUsuarioFlujoOrigen);
                procesoFlujo.setSUserCreacion(entidad.get(i).getActividadInvestigacion().getSUserCreacion());
                procesoFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
                procesoFlujo.setSEstado("A");
                procesoFlujo = procesoFlujoDao.Insert(procesoFlujo);
                
                detalleInvestigacionFlujo.setNIdProcesoFlujo(procesoFlujo.getNIdProcesoFlujo());
                detalleInvestigacionFlujo.setNIdActividadInvestigacion(entidad.get(i).getActividadInvestigacion().getNIdActividadInvestigacion());
                detalleInvestigacionFlujo.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                detalleInvestigacionFlujo.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
                detalleInvestigacionFlujo.setSEstado("A");
                detalleInvestigacionFlujo = detalleInvestigacionFlujoDao.Insert(detalleInvestigacionFlujo);
                
                /*Insertar DetalleMasiva*/
                SRIDetalleMasiva detalleMasiva = new SRIDetalleMasiva();
                detalleMasiva.setNIdCabeceraMasiva(cabeceraMasiva.getNIdCabeceraMasiva());
                detalleMasiva.setNIdActividadInvestigacion(entidad.get(i).getActividadInvestigacion().getNIdActividadInvestigacion());
                detalleMasiva.setNIdDetalleInvestigacionFlujo(detalleInvestigacionFlujo.getNIdDetalleInvestigacionFlujo());
                detalleMasiva.setSUserCreacion(usuarioOrigen.getSUsuarioLogin());
                detalleMasiva.setSUserModificacion(usuarioOrigen.getSUsuarioLogin());
                detalleMasiva.setSEstado("A");
                detalleMasiva = detalleMasivaDao.Insert(detalleMasiva);
                
            } catch(Exception ex) {
                throw ex;
            }
        }
        
        return entidad;
    }

    @Override
    public List<SRICabeceraDetalleMasiva> GetCabeceraMasiva(int idUsuario) {
        List<SRICabeceraDetalleMasiva> respuesta = null;
        try {
            respuesta = cabeceraMasivaDao.GetCabeceraMasiva(idUsuario);
        } catch (Exception ex) {
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public  Map<String, Object> GetDetalleMasiva(SRIPaginacion entidad) {
        int total = -1;
        List<SRIActividadGeneralPaginacion> lstActividadGeneral = null;
        Map<String, Object> respuesta = new HashMap<>();
        
        try{
            
            lstActividadGeneral = cabeceraMasivaDao.GetDetalleMasiva(entidad);
            total = 100;//cabeceraMasivaDao.GetTotalDetalleMasiva(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRITotalTipoActividad> GetTotalActividadesByTipoActividad(int idSemestre) {
        List<SRITotalTipoActividad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetTotalActividadesByTipoActividad(idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIDocentesActivosInactivosFacultad> GetActivosInactivosByFacultad(int idTipoInvestigacion, int idSemestre) {
        List<SRIDocentesActivosInactivosFacultad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetActivosInactivosByFacultad(idTipoInvestigacion, idSemestre);
        }
        catch(Exception ex){
            throw ex;   
        }
        return respuesta;
    }

    @Override
    public List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadFacultad(int idFacultad, int idSemestre) {
        List<SRITotalTipoActividad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetTotalActividadesByTipoActividadFacultad(idFacultad, idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosByDepartamento(int idFacultad, int idTipoInvestigacion, int idSemestre) {
        List<SRIDocentesActivosInactivosFacultad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetTotalActivosInactivosByDepartamento(idFacultad, idTipoInvestigacion, idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosHomeDepartamento(int idDepartamento, int idTipoInvestigacion, int idSemestre) {
        List<SRIDocentesActivosInactivosFacultad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetTotalActivosInactivosHomeDepartamento(idDepartamento, idTipoInvestigacion, idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadDepartamento(int idDepartamento,int idSemestre) {
        List<SRITotalTipoActividad> respuesta = null;
        try{
            respuesta = actividadInvestigacionDao.GetTotalActividadesByTipoActividadDepartamento(idDepartamento,idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }
    
    public int GetUsuarioFlujoOrigen (List<SRIFlujoActorUtil> lst){
        int respuesta = -1;
        int actorMayor = -1;
        int pesoDiun = 3;
        int pesoDide = 2;
        
        for(int i = 0; i < lst.size(); i++ ){
            switch(lst.get(i).getSCodigo()){
                case "DIUN" :
                    if(actorMayor < pesoDiun){
                        actorMayor = pesoDiun;
                        respuesta = lst.get(i).getNIdUsuarioFlujo();
                    }
                    break;
                case "DIDE" : 
                    if(actorMayor < pesoDide){
                        actorMayor = pesoDide;
                        respuesta = lst.get(i).getNIdUsuarioFlujo();
                    }
                    break;     
            }
        }
        
        return respuesta;
    }
    
    public int GetIdFujoActorOrigen (List<SRIFlujoActorUtil> lst){
        int respuesta = -1;
        int actorMayor = -1;
        int pesoDiun = 3;
        int pesoDide = 2;
        
        for(int i = 0; i < lst.size(); i++ ){
            switch(lst.get(i).getSCodigo()){
                case "DIUN" :
                    if(actorMayor < pesoDiun){
                        actorMayor = pesoDiun;
                        respuesta = lst.get(i).getNIdFlujoActor();
                    }
                    break;
                case "DIDE" : 
                    if(actorMayor < pesoDide){
                        actorMayor = pesoDide;
                        respuesta = lst.get(i).getNIdFlujoActor();
                    }
                    break;     
            }
        }
        
        return respuesta;
    }

    @Override
    public Map<String, Object> GetDocentesActivos(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        try{
            List<SRIDocentesActividades> lstActividadGeneral = actividadInvestigacionDao.GetDocentesActivos(entidad);
            int total = actividadInvestigacionDao.GetTotalDocentesActivos(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public Map<String, Object> GetDocentesActivosInactivos(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        try{
            List<SRIDocentesActividades> lstActividadGeneral = actividadInvestigacionDao.GetDocentesActivosInactivos(entidad);
            int total = actividadInvestigacionDao.GetTotalDocentesActivosInactivos(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos (SRIPaginacion entidad) {
        
        SRIDocenteActivosInactivos respuesta = new SRIDocenteActivosInactivos();
        try{
           respuesta = actividadInvestigacionDao.GetTotalRelacionDocentesActivosInactivos(entidad);

        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> GetDocentesInactivos(SRIPaginacion entidad) {
        
        Map<String, Object> respuesta = new HashMap<>();
        try{
            List<SRIDocentesActividades> lstActividadGeneral = actividadInvestigacionDao.GetDocentesInactivos(entidad);
            int total = actividadInvestigacionDao.GetTotalDocentesInactivos(entidad);
            respuesta.put("lista", lstActividadGeneral);
            respuesta.put("total", total);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean EliminarActividadGenerada(int idActividad) {
        boolean respuesta = false;
        try{
            respuesta = actividadInvestigacionDao.EliminarActividadGenerada(idActividad);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }
    
    @Override
    public boolean EnviarInforme(byte[] adjunto, SRIPaginacion entidad) {
        boolean respuesta = false;
        List<String> nombreDestinatarios = new ArrayList<String>();
        List<String> emailDestinatarios = new ArrayList<String>();
        try {
            //Get Remitente y destinatarios
            List<SRIUsuarioPersona> lstUsuarioPersonas = usuarioDao.GetDirectorDepartamentoByIdDocente(entidad.getIdUsuario());
            List<SRIUsuarioPersona> remitente = usuarioDao.GetUsuarioPersonaByIdUsuario(entidad.getIdUsuario());
            
            for(SRIUsuarioPersona usuarioPersona : lstUsuarioPersonas){
                nombreDestinatarios.add(usuarioPersona.getSNombre() + " " + usuarioPersona.getSApellido());
                emailDestinatarios.add(usuarioPersona.getSUsuarioEmail());
            }
            String nombreRemitente = remitente.get(0).getSNombre() + " " + remitente.get(0).getSApellido();
            String emailRemitente = remitente.get(0).getSUsuarioEmail();
            String cuerpo = "Le envio las actividades de investigación generadas para el semestre actual.";
            String asunto = "SIRI UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String titulo = "Actividades de Investigación Registradas";
            
            Email email = new Email();
            log.log(Level.INFO, "Email enable : {0}", email.recuperar());
            
            email.enviarInforme(nombreDestinatarios, nombreRemitente, cuerpo, emailDestinatarios, emailRemitente, titulo, asunto, adjunto);
            respuesta = true;  

        } catch(Exception ex) {
            try {
                throw ex;
            } catch (Exception ex1) {
                respuesta = false;
                Logger.getLogger(ActividadInvestigacionBusinessImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return respuesta;
    }
    
    @Override
    public boolean EnviarInformeDepartamento(byte[] adjunto, SRIPaginacion entidad) {
        boolean respuesta = false;
        List<String> nombreDestinatarios = new ArrayList<String>();
        List<String> emailDestinatarios = new ArrayList<String>();
        try {
            //Get Remitente y destinatarios
            List<SRIUsuarioPersona> lstUsuarioPersonas = usuarioDao.GetDirectorUnidadByIdDocente(entidad.getIdUsuario());
            List<SRIUsuarioPersona> remitente = usuarioDao.GetUsuarioPersonaByIdUsuario(entidad.getIdUsuario());
            
            for(SRIUsuarioPersona usuarioPersona : lstUsuarioPersonas){
                nombreDestinatarios.add(usuarioPersona.getSNombre() + " " + usuarioPersona.getSApellido());
                emailDestinatarios.add(usuarioPersona.getSUsuarioEmail());
            }
            String nombreRemitente = remitente.get(0).getSNombre() + " " + remitente.get(0).getSApellido();
            String emailRemitente = remitente.get(0).getSUsuarioEmail();
            String cuerpo = "Adjunto las Actividades de Investigación de los Docentes de mi Departamento,"
                            + " para su aprobación.";
            String asunto = "SIRI UNSA - SISTEMA DE REGISTRO DE ACTIVIDADES DE INVESTIGACION";
            String titulo = "Actividades de Investigación Registradas";
            
            Email email = new Email();
            log.log(Level.INFO, "Email enable : {0}", email.recuperar());
            
            email.enviarInforme(nombreDestinatarios, nombreRemitente, cuerpo, emailDestinatarios, emailRemitente, titulo, asunto, adjunto);
            respuesta = true;  

        } catch(Exception ex) {
            try {
                throw ex;
            } catch (Exception ex1) {
                respuesta = false;
                Logger.getLogger(ActividadInvestigacionBusinessImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return respuesta;
    }

    @Override
    public Map<String, Object> GetInformeDepartamento(SRIPaginacion entidad) {
        Map<String, Object> respuesta = new HashMap<>();
        try{
            List<SRIEnviarInformeDepartamento> informeDepartamento = actividadInvestigacionDao.GetInformeDepartamento(entidad);
            respuesta.put("lista", informeDepartamento);
        } catch(Exception ex) {
            throw ex;
        }
        return respuesta;
    }
}
