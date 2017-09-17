/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIDocente;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocenteExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioColor;
import com.innnovacis.unsa.util.SRIUsuarioHome;
import com.innnovacis.unsa.util.SRIUsuarioLogin;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IUsuarioBusiness {
    
    int Insertar(SRIUsuario entidad);
    boolean Update(SRIUsuario entidad);
    boolean Delete(SRIUsuario entidad);
    SRIUsuario Get(int idEntidad);
    SRIUsuario GetByIdUsuario(int idUsuario);
    List<SRIFlujoActor> GetActoresByIdUsuario(int idUsuario);
    
    int GetTotalUsuariosColor(SRIPaginacionObject entidad);
    List<SRIUsuarioColor> GetUsuariosColor(SRIPaginacionObject entidad);
    
    List<SRIUsuario> GetAll();
    SRIUsuarioLogin AutenticarUsuario(SRIUsuario entidad);
    
    int InsertarUsuarioPersona(SRIUsuarioPersona usuariopersona);
    int UpdateUsuarioPersona(SRIUsuarioPersona usuariopersona);
    int DeleteUsuarioPersona(SRIUsuarioPersona usuariopersona);
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIUsuarioPersona> GetPagina(SRIPaginacionObject entidad);
    
    SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos(int idSemestre);
    SRIDocenteActivosInactivos GetTotalDocentesActivosInactivosByFacultad(int idFacultad);
    List<SRIUsuarioHome> GetUsuarioHome(int idUsuario, int idUsuarioDirector);
    boolean enviarCodigo(int idUsuario);
    SRIUsuario verificarCodigo(SRIUsuario entidad);
    SRIDocente GetDocenteReporte(int idUsuario);
    
    int GetTotalUsuarioExoneracion(SRIPaginacionObject entidad);
    List<SRIDocenteExoneracion> GetListaUsuarioExoneracion(SRIPaginacionObject entidad);

    SRIUsuarioLogin LoginGoogle(String userEmail);
}
