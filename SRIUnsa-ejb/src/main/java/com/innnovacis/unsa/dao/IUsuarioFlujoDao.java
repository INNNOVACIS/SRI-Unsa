/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import com.innnovacis.unsa.util.SRIFlujoActorUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioFlujoUtil;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IUsuarioFlujoDao {
    SRIUsuarioFlujo Insert(SRIUsuarioFlujo entidad);
    SRIUsuarioFlujo Update(SRIUsuarioFlujo entidad);
    boolean Delete(SRIUsuarioFlujo entidad);
    SRIUsuarioFlujo GetById(int idEntidad);
    List<SRIUsuarioFlujo> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIUsuarioFlujoUtil> GetPagina(SRIPaginacionObject object);
    List<SRIUsuarioFlujo> getUsuarioFlujoByIdUsuario(int id);
    List<SRIFlujoActorUtil> getUsuarioFlujoActorByIdUsuario(int id);
    int CreateAndGetUsuarioFlujo(SRIUsuarioFlujo entidad);
}
