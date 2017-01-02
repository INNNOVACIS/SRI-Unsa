/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIFondoConcursable;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IFondoConcursableDao {
    
    SRIFondoConcursable Insert(SRIFondoConcursable entidad);
    SRIFondoConcursable Update(SRIFondoConcursable entidad);
    boolean Delete(SRIFondoConcursable entidad);
    SRIFondoConcursable GetById(int idEntidad);
    List<SRIFondoConcursable> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIFondoConcursable> GetPagina(SRIPaginacionObject entidad);
    
}
