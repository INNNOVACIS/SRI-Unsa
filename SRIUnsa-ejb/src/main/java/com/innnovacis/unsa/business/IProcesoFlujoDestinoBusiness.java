/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIProcesoFlujoDestino;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IProcesoFlujoDestinoBusiness {
    int Insertar(SRIProcesoFlujoDestino entidad);
    boolean Update(SRIProcesoFlujoDestino entidad);
    boolean Delete(SRIProcesoFlujoDestino entidad);
    SRIProcesoFlujoDestino Get(int idEntidad);
    List<SRIProcesoFlujoDestino> GetAll();

}
