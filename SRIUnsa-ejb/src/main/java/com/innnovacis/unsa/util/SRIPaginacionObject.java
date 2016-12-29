/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

/**
 *
 * @author will
 */
public class SRIPaginacionObject {
    
    private int currentPage;
    private int rango;
    private int total;
    private int idFacultad;
    private int idDepartamento;
    private int idTipoInvestigacion;
    private String filtro;

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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdTipoInvestigacion() {
        return idTipoInvestigacion;
    }

    public void setIdTipoInvestigacion(int idTipoInvestigacion) {
        this.idTipoInvestigacion = idTipoInvestigacion;
    }
    
}
