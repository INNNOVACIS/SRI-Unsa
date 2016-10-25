/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIEstructuraAreaInvestigacion;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IEstructuraAreaInvestigacionBusiness {
    int Insertar(SRIEstructuraAreaInvestigacion entidad);
    boolean Update(SRIEstructuraAreaInvestigacion entidad);
    boolean Delete(SRIEstructuraAreaInvestigacion entidad);
    SRIEstructuraAreaInvestigacion Get(int idEntidad);
    List<SRIEstructuraAreaInvestigacion> GetAll();

}
