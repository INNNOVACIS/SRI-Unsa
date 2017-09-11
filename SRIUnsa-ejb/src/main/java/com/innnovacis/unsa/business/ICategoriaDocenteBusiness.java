/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRICategoriaDocente;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ICategoriaDocenteBusiness {
    int Insertar(SRICategoriaDocente entidad);
    boolean Update(SRICategoriaDocente entidad);
    boolean Delete(SRICategoriaDocente entidad);
    SRICategoriaDocente Get(int idEntidad);
    List<SRICategoriaDocente> GetAll();
    SRICategoriaDocente GetById(int id);
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRICategoriaDocente> GetPagina(SRIPaginacionObject object);
}
