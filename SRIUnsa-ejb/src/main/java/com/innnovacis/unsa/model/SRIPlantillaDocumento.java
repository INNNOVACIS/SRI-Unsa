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
@Table(name = "plantilladocumento" )
@NamedQueries({
    @NamedQuery(name="SRIPlantillaDocumento.GetAll",query="SELECT p FROM SRIPlantillaDocumento p"),
    @NamedQuery(name="SRIPlantillaDocumento.GetById",query="SELECT p FROM SRIPlantillaDocumento p WHERE p.NIdPlantillaDocumento = :idEntidad")
})
public class SRIPlantillaDocumento  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idplantilladocumento" )
    private int NIdPlantillaDocumento;
    
    @Column(name = "etiqueta")
    private String SEtiqueta;

    @Column(name = "tipo")
    private String STipo;
    
    @Column(name = "facultad")
    private String SFacultad;
    
    @Column(name = "model")
    private String SModel;
    
    @Column(name = "opciones")
    private String SOpciones;
    
    @Column(name = "data")
    private String SData;
    
    @Column(name = "plantilla")
    private String SPlantilla;

    public String getSOpciones() {
        return SOpciones;
    }

    public void setSOpciones(String SOpciones) {
        this.SOpciones = SOpciones;
    }

    public int getNIdPlantillaDocumento() {
        return NIdPlantillaDocumento;
    }

    public void setNIdPlantillaDocumento(int NIdPlantillaDocumento) {
        this.NIdPlantillaDocumento = NIdPlantillaDocumento;
    }

    public String getSEtiqueta() {
        return SEtiqueta;
    }

    public void setSEtiqueta(String SEtiqueta) {
        this.SEtiqueta = SEtiqueta;
    }

    public String getSTipo() {
        return STipo;
    }

    public void setSTipo(String STipo) {
        this.STipo = STipo;
    }

    public String getSFacultad() {
        return SFacultad;
    }

    public void setSFacultad(String SFacultad) {
        this.SFacultad = SFacultad;
    }

    public String getSModel() {
        return SModel;
    }

    public void setSModel(String SModel) {
        this.SModel = SModel;
    }

    public String getSData() {
        return SData;
    }

    public void setSData(String SData) {
        this.SData = SData;
    }

    public String getSPlantilla() {
        return SPlantilla;
    }

    public void setSPlantilla(String SPlantilla) {
        this.SPlantilla = SPlantilla;
    }
    
}

