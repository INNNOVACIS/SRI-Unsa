/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneral;
import com.innnovacis.unsa.util.SRICabeceraDetalleMasiva;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocentesActivosInactivosFacultad;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRITotalTipoActividad;

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
    boolean Update(SRIActividadGeneral entidad);
    boolean Delete(SRIActividadInvestigacion entidad);
    SRIActividadGeneral Get(int idEntidad);
    List<SRIActividadInvestigacion> GetAll();
    
    SRIActividadGeneral GuardarInvestigacion(SRIActividadGeneral entidad);
    SRIActividadGeneral RegistrarActividad(SRIActividadGeneral entidad);
    SRIActividadGeneral AprobarActividadInvestigacion(SRIActividadGeneral entidad);
    List<SRIActividadGeneral> AprobarActividadInvestigacionMasivo(List<SRIActividadGeneral> entidad);
    Map<String, Object> GetActividadesGeneradasHomeDocente(SRIPaginacion entidad);
    Map<String, Object> GetActividadesPendientes(SRIPaginacion entidad);
    Map<String, Object> GetActividadesGeneradas(SRIPaginacion entidad);
    Map<String, Object> GetActividadesRevisadas(SRIPaginacion entidad);
    Map<String, Object> GetActividadesRevisadasMasivas(SRIPaginacion entidad);
    Map<String, Object> GetActividadesByDocente(SRIPaginacion entidad);
    Map<String, Object> GetActividadesByDocenteColaboradores(SRIPaginacion entidad);
    Map<String, Object> GetActividadesByDocenteDetalle(SRIPaginacion entidad);
    boolean EnviarEmail(SRIActividadGeneral entidad);
    boolean EnviarInforme(byte[] informe, SRIPaginacion entidad);
    boolean EnviarInformeDepartamento(byte[] informe, SRIPaginacion entidad);
    
    List<SRICabeceraDetalleMasiva> GetCabeceraMasiva(int idUsuarioFlujo);
    Map<String, Object> GetDetalleMasiva(SRIPaginacion entidad);
    
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividad(int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetActivosInactivosByFacultad(int idTipoInvestigacion, int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosByDepartamento(int idFacultad, int idTipoInvestigacion, int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosHomeDepartamento(int idDepartamento, int idTipoInvestigacion, int idSemestre);
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadFacultad(int idFacultad, int idSemestre);
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadDepartamento(int idDepartamento,int idSemestre);
    
    Map<String, Object> GetDocentesActivos(SRIPaginacion entidad);
    Map<String, Object> GetDocentesActivosInactivos(SRIPaginacion entidad);
    SRIDocenteActivosInactivos GetTotalDocentesActivosInactivos(SRIPaginacion entidad);
    Map<String, Object> GetDocentesInactivos(SRIPaginacion entidad);
    boolean EliminarActividadGenerada(int idActividad);
    
    Map<String, Object> GetInformeDepartamento(SRIPaginacion entidad);
}
