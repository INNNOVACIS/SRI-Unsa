/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIPaginacion;
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
    
    List<SRIActividadGeneralPaginacion> GetPagina(SRIPaginacion pagina, String idActividades);
    int GetTotalPagina(SRIPaginacion pagina, String idActividades);
    
    int GetTotalActividadesGeneradas(SRIPaginacion entidad);
    int GetTotalActividadesPendientes(SRIPaginacion entidad);
    int GetTotalActividadesRevisadas(SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesGeneradas(SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesPendientes(SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesRevisadas(SRIPaginacion entidad);
}
