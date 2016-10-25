/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IActividadInvestigacionDao {
    SRIActividadInvestigacion Insert(SRIActividadInvestigacion entidad);
    SRIActividadInvestigacion Update(SRIActividadInvestigacion entidad);
    boolean Delete(SRIActividadInvestigacion entidad);
    SRIActividadInvestigacion GetById(int idEntidad);
    List<SRIActividadInvestigacion> GetAll();  
}
