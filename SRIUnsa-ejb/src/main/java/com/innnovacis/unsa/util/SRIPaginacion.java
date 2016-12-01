/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import java.io.Serializable;

/**
 *
 * @author will
 */

public class SRIPaginacion implements Serializable {
    
    private int currentPage;
    
    private int rango;
    
    private int total;
    
    private int idUsuario;
    
    private int idEstado;
    
    private int idFlujoActor;
    
    private SRIActividadInvestigacion filtro;

    public int getIdFlujoActor() {
        return idFlujoActor;
    }

    public void setIdFlujoActor(int idFlujoActor) {
        this.idFlujoActor = idFlujoActor;
    }

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

    public SRIActividadInvestigacion getFiltro() {
        return filtro;
    }

    public void setFiltro(SRIActividadInvestigacion filtro) {
        this.filtro = filtro;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
