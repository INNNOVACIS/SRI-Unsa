/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIUsuario;
import java.io.Serializable;

/**
 *
 * @author will
 */

public class SRIUsuariosPaginacion implements Serializable {
    
    private int currentPage;
    private int rango;
    private int total;
    private SRIUsuario filtro;

    
    public SRIUsuario getFiltro() {
        return filtro;
    }

    public void setFiltro(SRIUsuario filtro) {
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
