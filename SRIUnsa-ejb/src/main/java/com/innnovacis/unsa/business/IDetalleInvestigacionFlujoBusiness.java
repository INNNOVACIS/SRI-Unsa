/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIDetalleInvestigacionFlujo;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IDetalleInvestigacionFlujoBusiness {
    int Insertar(SRIDetalleInvestigacionFlujo entidad);
    boolean Update(SRIDetalleInvestigacionFlujo entidad);
    boolean Delete(SRIDetalleInvestigacionFlujo entidad);
    SRIDetalleInvestigacionFlujo Get(int idEntidad);
    List<SRIDetalleInvestigacionFlujo> GetAll();

}
