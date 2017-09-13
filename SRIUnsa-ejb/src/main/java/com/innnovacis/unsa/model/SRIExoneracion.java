package com.innnovacis.unsa.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "exoneracion" )
@NamedQueries({
    @NamedQuery(name="SRIExoneracion.GetAll",query="SELECT p FROM SRIExoneracion p"),
    @NamedQuery(name="SRIExoneracion.GetById",query="SELECT p FROM SRIExoneracion p WHERE p.NIdExoneracion = :idEntidad")
})
public class SRIExoneracion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idexoneracion" )
    private int NIdExoneracion;
    
    @Column(name = "nombre")
    private String SNombre;
    
    @Column(name = "descripcion")
    private String SDescripcion;

    public int getNIdExoneracion() {
        return NIdExoneracion;
    }

    public void setNIdExoneracion(int NIdExoneracion) {
        this.NIdExoneracion = NIdExoneracion;
    }

    public String getSNombre() {
        return SNombre;
    }

    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }
    
    public String getSDescripcion() {
        return SDescripcion;
    }

    public void setSDescripcion(String SDescripcion) {
        this.SDescripcion = SDescripcion;
    }
    
}

