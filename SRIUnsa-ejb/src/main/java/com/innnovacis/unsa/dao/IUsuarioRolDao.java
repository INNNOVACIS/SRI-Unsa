/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIUsuarioRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IUsuarioRolDao {
    SRIUsuarioRol Insert(SRIUsuarioRol entidad);
    SRIUsuarioRol Update(SRIUsuarioRol entidad);
    boolean Delete(SRIUsuarioRol entidad);
    SRIUsuarioRol GetById(int idEntidad);
    List<SRIUsuarioRol> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIUsuarioRolUtil> GetPagina(SRIPaginacionObject object);
}
