/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IRolBusiness {
    int Insertar(SRIRol entidad);
    boolean Update(SRIRol entidad);
    boolean Delete(SRIRol entidad);
    SRIRol Get(int idEntidad);
    List<SRIRol> GetAll();
    
    List<SRIRol> GetPagina(SRIPaginacionObject object);
    int GetTotalPaginacion(SRIPaginacionObject object);

}
