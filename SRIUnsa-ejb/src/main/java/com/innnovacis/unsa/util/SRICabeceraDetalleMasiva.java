/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;


/**
 *
 * @author will
 */
@Entity
@Immutable
@Subselect("")
public class SRICabeceraDetalleMasiva implements Serializable{
    
    @Id
    @Column(name = "idcabeceramasiva")
    private int NIdCabeceraMasiva;
    
    @Column(name = "usuario")
    private String  SUsuario;
    
    @Column(name = "total")
    private int NTotal;
    
    @Column(name = "fechacreacion")
    private String SFechaCreacion;

    public int getNIdCabeceraMasiva() {
        return NIdCabeceraMasiva;
    }

    public void setNIdCabeceraMasiva(int NIdCabeceraMasiva) {
        this.NIdCabeceraMasiva = NIdCabeceraMasiva;
    }

    public String getSUsuario() {
        return SUsuario;
    }

    public void setSUsuario(String SUsuario) {
        this.SUsuario = SUsuario;
    }

    public int getNTotal() {
        return NTotal;
    }

    public void setNTotal(int NTotal) {
        this.NTotal = NTotal;
    }

    public String getSFechaCreacion() {
        return SFechaCreacion;
    }

    public void setSFechaCreacion(String SFechaCreacion) {
        this.SFechaCreacion = SFechaCreacion;
    }

    
    
}
