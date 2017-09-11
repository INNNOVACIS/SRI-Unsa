/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRICategoriaDocente;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface ICategoriaDocenteDao {
    SRICategoriaDocente Insert(SRICategoriaDocente entidad);
    SRICategoriaDocente Update(SRICategoriaDocente entidad);
    boolean Delete(SRICategoriaDocente entidad);
    SRICategoriaDocente GetById(int idEntidad);
    List<SRICategoriaDocente> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRICategoriaDocente> GetPagina(SRIPaginacionObject object);
}
