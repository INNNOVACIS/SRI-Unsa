/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IActividadInvestigacionBusiness {
    int Insertar(SRIActividadInvestigacion entidad);
    boolean Update(SRIActividadInvestigacion entidad);
    boolean Delete(SRIActividadInvestigacion entidad);
    SRIActividadInvestigacion Get(int idEntidad);
    List<SRIActividadInvestigacion> GetAll();

}
