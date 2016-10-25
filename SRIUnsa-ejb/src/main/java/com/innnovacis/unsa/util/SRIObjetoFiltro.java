/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIUsuario;
import java.util.List;

/**
 *
 * @author innnovacis
 */
public class SRIObjetoFiltro {
    
    private int numeroPagina;
    private int rangoPagina;
    private String parametro;

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
   
    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getRangoPagina() {
        return rangoPagina;
    }

    public void setRangoPagina(int rangoPagina) {
        this.rangoPagina = rangoPagina;
    }

   

   
}
