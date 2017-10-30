
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.util.SRIActividadGeneralPaginacion;
import com.innnovacis.unsa.util.SRIDocenteActivosInactivos;
import com.innnovacis.unsa.util.SRIDocentesActividades;
import com.innnovacis.unsa.util.SRIDocentesActivosInactivosFacultad;
import com.innnovacis.unsa.util.SRIEnviarInformeDepartamento;
import com.innnovacis.unsa.util.SRIPaginacion;
import com.innnovacis.unsa.util.SRITotalTipoActividad;
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
    
    int GetTotalActividadesGeneradasHomeDocente (SRIPaginacion entidad);
    int GetTotalActividadesGeneradas (SRIPaginacion entidad);
    int GetTotalActividadesPendientes(SRIPaginacion entidad);
    int GetTotalActividadesRevisadas (SRIPaginacion entidad);
    int GetTotalActividadesByDocente (SRIPaginacion entidad);
    int GetTotalActividadesByDocenteDetalle (SRIPaginacion entidad);
    int GetTotalActividadesByDocenteColaboradores (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesGeneradasHomeDocente (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesGeneradas (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesPendientes(SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesRevisadas (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesRevisadasMasivas (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesByDocente (SRIPaginacion entidad);
    List<SRIActividadGeneralPaginacion> GetActividadesByDocenteColaboradores (SRIPaginacion entidad);
    List<SRIActividadInvestigacion> GetActividadesByDocenteDetalle (SRIPaginacion entidad);
    
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividad(int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetActivosInactivosByFacultad(int idTipoInvestigacion, int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosByDepartamento(int idFacultad, int idTipoInvestigacion, int idSemestre);
    List<SRIDocentesActivosInactivosFacultad> GetTotalActivosInactivosHomeDepartamento(int idDepartamento, int idTipoInvestigacion, int idSemestre);
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadFacultad(int idFacultad, int idSemestre);
    List<SRITotalTipoActividad> GetTotalActividadesByTipoActividadDepartamento(int idDepartamento,int idSemestre);
    
    List<SRIDocentesActividades> GetDocentesActivos(SRIPaginacion entidad);
    List<SRIDocentesActividades> GetDocentesActivosInactivos(SRIPaginacion entidad);
    SRIDocenteActivosInactivos GetTotalRelacionDocentesActivosInactivos(SRIPaginacion entidad);
    List<SRIDocentesActividades> GetDocentesInactivos(SRIPaginacion entidad);
    int GetTotalDocentesActivos(SRIPaginacion entidad);
    int GetTotalDocentesActivosInactivos(SRIPaginacion entidad);
    int GetTotalDocentesInactivos(SRIPaginacion entidad);
    boolean EliminarActividadGenerada(int idActividad);
    List<SRIEnviarInformeDepartamento> GetInformeDepartamento(SRIPaginacion entidad);
}

