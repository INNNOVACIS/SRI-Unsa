/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
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
public class SRIActividadesRevisadas implements Serializable {
    
    @Id
    @Column(name = "idactividadinvestigacion" )
    private int idactividadinvestigacion;
    
    @Column(name = "nombreactividad" )
    private String nombreactividad;
    
    @Column(name = "tipoactividad" )
    private String tipoactividad;
    
    @Column(name = "horas" )
    private int horas;
    
    @Column(name = "cantidad" )
    private int cantidad;

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

    public int getIdactividadinvestigacion() {
        return idactividadinvestigacion;
    }

    public void setIdactividadinvestigacion(int idactividadinvestigacion) {
        this.idactividadinvestigacion = idactividadinvestigacion;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
