/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIFondoConcursable;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IFondoConcursableBusiness {
    int Insertar(SRIFondoConcursable entidad);
    boolean Update(SRIFondoConcursable entidad);
    boolean Delete(SRIFondoConcursable entidad);
    SRIFondoConcursable Get(int idEntidad);
    List<SRIFondoConcursable> GetAll();
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIFondoConcursable> GetPagina(SRIPaginacionObject object);

}
