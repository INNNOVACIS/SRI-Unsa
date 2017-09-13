/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIExoneracion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IExoneracionDao {
    SRIExoneracion Insert(SRIExoneracion entidad);
    SRIExoneracion Update(SRIExoneracion entidad);
    boolean Delete(SRIExoneracion entidad);
    SRIExoneracion GetById(int idEntidad);
    List<SRIExoneracion> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIExoneracion> GetPagina(SRIPaginacionObject object);
}
