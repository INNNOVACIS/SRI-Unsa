/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIPlanificacionActividad;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPlanificacionActividadBusiness {
    int Insertar(SRIPlanificacionActividad entidad);
    boolean Update(SRIPlanificacionActividad entidad);
    boolean Delete(SRIPlanificacionActividad entidad);
    SRIPlanificacionActividad Get(int idEntidad);
    List<SRIPlanificacionActividad> GetAll();

}
