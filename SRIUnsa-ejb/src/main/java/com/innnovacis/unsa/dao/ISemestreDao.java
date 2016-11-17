/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRISemestre;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ISemestreDao {
    SRISemestre Insert(SRISemestre entidad);
    SRISemestre Update(SRISemestre entidad);
    boolean Delete(SRISemestre entidad);
    SRISemestre GetById(int idEntidad);
    List<SRISemestre> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRISemestre> GetPagina(SRIPaginacionObject object);
    
}
