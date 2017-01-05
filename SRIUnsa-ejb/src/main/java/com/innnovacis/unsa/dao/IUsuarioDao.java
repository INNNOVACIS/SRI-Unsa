/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
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
public interface IUsuarioDao {
    
    SRIUsuario Insert(SRIUsuario entidad);
    SRIUsuario Update(SRIUsuario entidad);
    boolean Delete(SRIUsuario entidad);
    SRIUsuario GetById(int idEntidad);
    List<SRIUsuario> GetAll();
    SRIUsuario GetByIdUsuario(int idUsuario);
    List<SRIFlujoActor> GetActoresByIdUsuario(int idUsuario);
    
    List<SRIUsuarioColor> GetUsuariosColor(SRIPaginacionObject entidad);
    int GetTotalUsuariosColor(SRIPaginacionObject entidad);
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIUsuarioPersona> GetPagina(SRIPaginacionObject entidad);
    SRIUsuarioLogin AutenticarUsuario(SRIUsuario entidad);
    List<SRIUsuarioPersona> GetDestinatariosByCodigoActorDestino(String codigoActorDestino);
    List<SRIUsuarioPersona> GetUsuarioPersonaByIdUsuario(int idUsuario);
    
    SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos();
    SRIDocenteActivosInactivos GetTotalDocentesActivosInactivosByFacultad(int idFacultad);
    List<SRIUsuarioHome> GetUsuarioHome(int idUsuario, int idUsuarioDirector);
}
