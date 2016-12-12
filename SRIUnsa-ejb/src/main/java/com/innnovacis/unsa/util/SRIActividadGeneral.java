/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import com.innnovacis.unsa.model.SRIPersona;
import java.util.List;


/**
 *
 * @author will
 */
public class SRIActividadGeneral {
    
    private int idUsuario;
    
    private int idEstado;
    
    private int idFlujoActorOrigen;
    
    private int idPlanificacion;
    
    private SRIActividadInvestigacion actividadInvestigacion;
    
    private List<SRIPersona> colaboradores;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdFlujoActorOrigen() {
        return idFlujoActorOrigen;
    }

    public void setIdFlujoActorOrigen(int idFlujoActorOrigen) {
        this.idFlujoActorOrigen = idFlujoActorOrigen;
    }

    public SRIActividadInvestigacion getActividadInvestigacion() {
        return actividadInvestigacion;
    }

    public void setActividadInvestigacion(SRIActividadInvestigacion actividadInvestigacion) {
        this.actividadInvestigacion = actividadInvestigacion;
    }

    public int getIdPlanificacion() {
        return idPlanificacion;
    }

    public void setIdPlanificacion(int idPlanificacion) {
        this.idPlanificacion = idPlanificacion;
    }

    public List<SRIPersona> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<SRIPersona> colaboradores) {
        this.colaboradores = colaboradores;
    }
    
}
