/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import com.innnovacis.unsa.util.SRIPaginacion;

import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IActividadInvestigacionBusiness {
    
    int Insertar(SRIActividadInvestigacion entidad);
    boolean Update(SRIActividadInvestigacion entidad);
    boolean Delete(SRIActividadInvestigacion entidad);
    SRIActividadInvestigacion Get(int idEntidad);
    List<SRIActividadInvestigacion> GetAll();
    
    SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad);
    SRIActividadGeneral AprobarActividadInvestigacion(SRIActividadGeneral entidad);
    Map<String, Object> GetActividadesPendientes(SRIPaginacion entidad);
    Map<String, Object> GetActividadesGeneradas(SRIPaginacion entidad);
    Map<String, Object> GetActividadesRevisadas(SRIPaginacion entidad);
    boolean EnviarEmail(SRIActividadGeneral entidad);
}
