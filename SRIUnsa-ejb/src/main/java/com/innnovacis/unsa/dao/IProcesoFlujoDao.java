/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIProcesoFlujo;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IProcesoFlujoDao {
    SRIProcesoFlujo Insert(SRIProcesoFlujo entidad);
    SRIProcesoFlujo Update(SRIProcesoFlujo entidad);
    boolean Delete(SRIProcesoFlujo entidad);
    SRIProcesoFlujo GetById(int idEntidad);
    List<SRIProcesoFlujo> GetAll();  
}
