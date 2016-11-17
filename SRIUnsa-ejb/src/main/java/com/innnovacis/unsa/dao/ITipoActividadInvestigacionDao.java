/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoActividadInvestigacionDao {
    SRITipoActividadInvestigacion Insert(SRITipoActividadInvestigacion entidad);
    SRITipoActividadInvestigacion Update(SRITipoActividadInvestigacion entidad);
    boolean Delete(SRITipoActividadInvestigacion entidad);
    SRITipoActividadInvestigacion GetById(int idEntidad);
    List<SRITipoActividadInvestigacion> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRITipoActividadInvestigacion> GetPagina(SRIPaginacionObject object);
    
}
