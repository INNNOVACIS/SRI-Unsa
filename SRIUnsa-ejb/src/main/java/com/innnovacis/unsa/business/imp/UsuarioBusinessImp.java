
package com.innnovacis.unsa.business.imp;


import com.innnovacis.unsa.business.IUsuarioBusiness;
import com.innnovacis.unsa.dao.IPersonaDao;

import com.innnovacis.unsa.dao.IUsuarioDao;
import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIPersona;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.Email;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioHome;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import java.util.ArrayList;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;



@RequestScoped
public class UsuarioBusinessImp implements IUsuarioBusiness {

    @Inject
    private IUsuarioDao usuarioDao;
    
    @Inject
    private IPersonaDao personaDao;

    @Override
    public int Insertar(SRIUsuario entidad) {
        int id = -1;
        try{
            entidad = usuarioDao.Insert(entidad);
            id = entidad.getNIdUsuario();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }

    @Override
    public boolean Update(SRIUsuario entidad) {
        boolean respuesta = false;
        try{
            usuarioDao.Update(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
        
    }

    @Override
    public boolean Delete(SRIUsuario entidad) {
        boolean respuesta = false;
        try{
            usuarioDao.Delete(entidad);
            respuesta = true;
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public SRIUsuario Get(int idEntidad) {
        SRIUsuario respuesta = null;
        try{
            respuesta = usuarioDao.GetById(idEntidad);
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }

    @Override
    public List<SRIUsuario> GetAll() {
        List<SRIUsuario> respuesta = null;
         try{
            respuesta = usuarioDao.GetAll();
        }
        catch(Exception ex){
            throw ex;
        }
         return respuesta;
    }


    @Override
    public List<SRIUsuarioPersona> GetPagina(SRIPaginacionObject entidad) {
        List<SRIUsuarioPersona> respuesta = null;
         try{
            respuesta = usuarioDao.GetPagina(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        int respuesta = -1;
        try{
            respuesta = usuarioDao.GetTotalPaginacion(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIUsuarioLogin AutenticarUsuario(SRIUsuario entidad) {
        SRIUsuarioLogin respuesta = null;
        try{
            respuesta = usuarioDao.AutenticarUsuario(entidad);
        }
        catch(Exception ex){
            throw  ex;
        }
        return respuesta;
    }

    @Override
    public int InsertarUsuarioPersona(SRIUsuarioPersona usuariopersona) {
        int respuesta = -1;
        SRIUsuario usuario = new SRIUsuario();
        SRIPersona persona = new SRIPersona();
        try{
            persona.setSNombre(usuariopersona.getSNombre());
            persona.setSApellido(usuariopersona.getSApellido());
            persona.setNDni(usuariopersona.getNDni());
            persona.setSEmail(usuariopersona.getSUsuarioEmail());
            persona.setSUserCreacion(usuariopersona.getSUserCreacion());
            persona.setSUserModificacion(usuariopersona.getSUserCreacion());
            persona.setSEstado(usuariopersona.getSEstado());
            
            usuario.setSUsuarioLogin(usuariopersona.getSUsuarioLogin());
            usuario.setSUsuarioPassword(usuariopersona.getSUsuarioPassword());
            usuario.setSUsuarioEmail(usuariopersona.getSUsuarioEmail());
            usuario.setNIdEstructuraOrganizacion(usuariopersona.getNIdEstructuraOrganizacion());
            usuario.setNIdDepartamento(usuariopersona.getNidDepartamento());
            usuario.setSUserCreacion(usuariopersona.getSUserCreacion());
            usuario.setSUserModificacion(usuariopersona.getSUserCreacion());
            usuario.setSEstado(usuariopersona.getSEstado());
            
            persona = personaDao.Insert(persona);
            usuario.setNIdPersona(persona.getNIdPersona());
            usuario = usuarioDao.Insert(usuario);
            
            respuesta = usuario.getNIdUsuario();
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int UpdateUsuarioPersona(SRIUsuarioPersona usuariopersona) {
        int respuesta = -1;
        SRIUsuario usuario = new SRIUsuario();
        SRIPersona persona = new SRIPersona();
        try{
            persona.setNIdPersona(usuariopersona.getNIdPersona());
            persona.setSNombre(usuariopersona.getSNombre());
            persona.setSApellido(usuariopersona.getSApellido());
            persona.setNDni(usuariopersona.getNDni());
            persona.setSEmail(usuariopersona.getSUsuarioEmail());
            persona.setSUserCreacion(usuariopersona.getSUserCreacion());
            persona.setSEstado(usuariopersona.getSEstado());
            
            usuario.setNIdUsuario(usuariopersona.getNIdUsuario());
            usuario.setNIdPersona(usuariopersona.getNIdPersona());
            usuario.setNIdEstructuraOrganizacion(usuariopersona.getNIdEstructuraOrganizacion());
            usuario.setNIdDepartamento(usuariopersona.getNidDepartamento());
            usuario.setSUsuarioLogin(usuariopersona.getSUsuarioLogin());
            usuario.setSUsuarioPassword(usuariopersona.getSUsuarioPassword());
            usuario.setSUsuarioEmail(usuariopersona.getSUsuarioEmail());
            usuario.setSUserCreacion(usuariopersona.getSUserCreacion());
            usuario.setSEstado(usuariopersona.getSEstado());
            
            persona = personaDao.Update(persona);
            usuario = usuarioDao.Update(usuario);
            
            respuesta = persona.getNIdPersona();
            respuesta = usuario.getNIdUsuario();
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int DeleteUsuarioPersona(SRIUsuarioPersona usuariopersona) {
        int respuesta = -1;
        SRIUsuario usuario = new SRIUsuario();
        SRIPersona persona = new SRIPersona();
        try{
            persona.setNIdPersona(usuariopersona.getNIdPersona());
            persona.setSNombre(usuariopersona.getSNombre());
            persona.setSApellido(usuariopersona.getSApellido());
            persona.setNDni(usuariopersona.getNDni());
            persona.setSEmail(usuariopersona.getSUsuarioEmail());
            persona.setSUserCreacion(usuariopersona.getSUserCreacion());
            persona.setSEstado(usuariopersona.getSEstado());
            
            usuario.setNIdUsuario(usuariopersona.getNIdUsuario());
            usuario.setNIdPersona(usuariopersona.getNIdPersona());
            usuario.setNIdEstructuraOrganizacion(usuariopersona.getNIdEstructuraOrganizacion());
            usuario.setSUsuarioLogin(usuariopersona.getSUsuarioLogin());
            usuario.setSUsuarioPassword(usuariopersona.getSUsuarioPassword());
            usuario.setSUsuarioEmail(usuariopersona.getSUsuarioEmail());
            usuario.setSUserCreacion(usuariopersona.getSUserCreacion());
            usuario.setSEstado(usuariopersona.getSEstado());
            
            personaDao.Delete(persona);
            usuarioDao.Delete(usuario);
            
            respuesta = persona.getNIdPersona();
            respuesta = usuario.getNIdUsuario();
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIUsuario GetByIdUsuario(int idUsuario) {
        SRIUsuario respuesta = null;
        try{
            respuesta = usuarioDao.GetByIdUsuario(idUsuario);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIFlujoActor> GetActoresByIdUsuario(int idUsuario) {
        List<SRIFlujoActor> respuesta = null;
        try{
            respuesta = usuarioDao.GetActoresByIdUsuario(idUsuario);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioColor> GetUsuariosColor(SRIPaginacionObject entidad) {
        List<SRIUsuarioColor> respuesta = null;
        try{
            respuesta = usuarioDao.GetUsuariosColor(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int GetTotalUsuariosColor(SRIPaginacionObject entidad) {
        int respuesta = -1;
        try{
            respuesta = usuarioDao.GetTotalUsuariosColor(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos(int idSemestre) {
        SRIDocenteActivosInactivos respuesta = null;
        try{
            respuesta = usuarioDao.GetTotalDocentesActivosInactivos(idSemestre);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public SRIDocenteActivosInactivos GetTotalDocentesActivosInactivosByFacultad(int idFacultad) {
        SRIDocenteActivosInactivos respuesta = null;
        try{
            respuesta = usuarioDao.GetTotalDocentesActivosInactivosByFacultad(idFacultad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioHome> GetUsuarioHome(int idUsuario, int idUsuarioDirector) {
        List<SRIUsuarioHome> respuesta = null;
        try{
            respuesta = usuarioDao.GetUsuarioHome(idUsuario, idUsuarioDirector);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public boolean enviarCodigo(int idUsuario) {
        SRIUsuario entidad = null;
        boolean respuesta = false;
        try{
            entidad = usuarioDao.enviarCodigo(idUsuario);
            Email email = new Email();
            List<String> destinatarios = new ArrayList<String>();
            destinatarios.add(entidad.getSUsuarioEmail());
            email.enviarCodigoEmail(entidad.getSCodigo(), destinatarios);
        }
        catch(Exception ex){
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(UsuarioBusinessImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return respuesta;
    }

    @Override
    public SRIUsuario verificarCodigo(SRIUsuario entidad) {
        SRIUsuario respuesta = null;
        try{
            respuesta = usuarioDao.verificarCodigo(entidad);
        }
        catch(Exception ex){
                throw ex;

        }
        return respuesta;
    }

    @Override
    public SRIDocente GetDocenteReporte(int idUsuario) {
        SRIDocente respuesta = null;
        try{
            respuesta = usuarioDao.GetDocenteReporte(idUsuario);
        }
        catch(Exception ex){
                throw ex;
        }
        return respuesta;
    }

    @Override
    public List<SRIUsuarioPersona> GetListaUsuarioExoneracion(SRIPaginacionObject entidad) {
        List<SRIUsuarioPersona> respuesta = null;
         try{
            respuesta = usuarioDao.GetListaUsuarioExoneracion(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }

    @Override
    public int GetTotalUsuarioExoneracion(SRIPaginacionObject entidad) {
        int respuesta = -1;
        try{
            respuesta = usuarioDao.GetTotalUsuarioExoneracion(entidad);
        }
        catch(Exception ex){
            throw ex;
        }
        return respuesta;
    }
    
}
