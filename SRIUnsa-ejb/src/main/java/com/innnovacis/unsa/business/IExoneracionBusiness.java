/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIExoneracion;
import com.innnovacis.unsa.model.SRIUsuario;
import com.innnovacis.unsa.model.SRIUsuarioExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IExoneracionBusiness {
    int Insertar(SRIExoneracion entidad);
    boolean Update(SRIExoneracion entidad);
    boolean Delete(SRIExoneracion entidad);
    SRIExoneracion Get(int idEntidad);
    List<SRIExoneracion> GetAll();
    SRIExoneracion GetById(int id);
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIExoneracion> GetPagina(SRIPaginacionObject object);
    
    SRIUsuario RegistrarUsuarioExoneracion(SRIUsuarioExoneracion usuarioExoneracion);
    SRIUsuario DeleteUsuarioExoneracion(int idUsuario);
}
