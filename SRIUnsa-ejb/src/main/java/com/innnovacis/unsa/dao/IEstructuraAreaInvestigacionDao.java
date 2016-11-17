/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IEstructuraAreaInvestigacionDao {
    
    SRIEstructuraAreaInvestigacion Insert(SRIEstructuraAreaInvestigacion entidad);
    SRIEstructuraAreaInvestigacion Update(SRIEstructuraAreaInvestigacion entidad);
    boolean Delete(SRIEstructuraAreaInvestigacion entidad);
    SRIEstructuraAreaInvestigacion GetById(int idEntidad);
    List<SRIEstructuraAreaInvestigacion> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIEstructuraAreaInvestigacion> GetPagina(SRIPaginacionObject object);
    
}
