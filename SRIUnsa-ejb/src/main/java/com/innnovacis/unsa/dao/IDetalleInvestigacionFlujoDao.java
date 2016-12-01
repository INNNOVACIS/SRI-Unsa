/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IDetalleInvestigacionFlujoDao {
    SRIDetalleInvestigacionFlujo Insert(SRIDetalleInvestigacionFlujo entidad);
    SRIDetalleInvestigacionFlujo Update(SRIDetalleInvestigacionFlujo entidad);
    boolean Delete(SRIDetalleInvestigacionFlujo entidad);
    SRIDetalleInvestigacionFlujo GetById(int idEntidad);
    List<SRIDetalleInvestigacionFlujo> GetAll();
    List<SRIDetalleInvestigacionFlujo> GetByIdProcesoFlujo(String stringIdProcesoFlujo);
    SRIDetalleInvestigacionFlujo GetByIdActividad(int idActividad);
}
