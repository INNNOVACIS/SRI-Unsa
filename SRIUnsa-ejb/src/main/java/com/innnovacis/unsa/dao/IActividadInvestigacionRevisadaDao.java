/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IActividadInvestigacionRevisadaDao {
    
    List<SRIActividadGeneralPaginacion> GetAll();
    List<SRIActividadGeneralPaginacion> GetPagina(SRIPaginacion entidad);
    int GetTotalPaginacion(SRIPaginacion entidad);
}
