/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioPersona;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
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
    
    List<SRIUsuario> GetAll();
    SRIUsuarioRolUtil AutenticarUsuario(SRIUsuario entidad);
    
    int InsertarUsuarioPersona(SRIUsuarioPersona usuariopersona);
    int UpdateUsuarioPersona(SRIUsuarioPersona usuariopersona);
    int DeleteUsuarioPersona(SRIUsuarioPersona usuariopersona);
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIUsuarioPersona> GetPagina(SRIPaginacionObject entidad);
    
}
