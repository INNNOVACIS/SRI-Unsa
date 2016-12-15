/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRICabeceraMasiva;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import com.innnovacis.unsa.util.SRIPaginacion;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ICabeceraMasivaDao {
    
    SRICabeceraMasiva Insert(SRICabeceraMasiva entidad);
    SRICabeceraMasiva Update(SRICabeceraMasiva entidad);
    SRICabeceraMasiva GetById(int idEntidad);
    boolean Delete(SRICabeceraMasiva entidad);
    
    List<SRICabeceraDetalleMasiva> GetCabeceraMasiva(int idUsuarioFlujo);
    List<SRIActividadGeneralPaginacion> GetDetalleMasiva(SRIPaginacion entidad);
    int GetTotalDetalleMasiva(SRIPaginacion entidad);
}
