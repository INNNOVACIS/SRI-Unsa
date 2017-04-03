/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIPlanificacionActividad;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPlanificacionActividadDao {
    SRIPlanificacionActividad Insert(SRIPlanificacionActividad entidad);
    SRIPlanificacionActividad Update(SRIPlanificacionActividad entidad);
    boolean Delete(SRIPlanificacionActividad entidad);
    SRIPlanificacionActividad GetById(int idEntidad);
    SRIPlanificacionActividad GetByIdActividad(int idActividad);
    List<SRIPlanificacionActividad> GetAll();
    
}
