/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIProcesoFlujoDestino;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IProcesoFlujoDestinoDao {
    SRIProcesoFlujoDestino Insert(SRIProcesoFlujoDestino entidad);
    SRIProcesoFlujoDestino Update(SRIProcesoFlujoDestino entidad);
    boolean Delete(SRIProcesoFlujoDestino entidad);
    SRIProcesoFlujoDestino GetById(int idEntidad);
    List<SRIProcesoFlujoDestino> GetAll();  
}
