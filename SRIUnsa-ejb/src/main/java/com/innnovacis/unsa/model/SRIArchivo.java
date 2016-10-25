package com.innnovacis.unsa.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "archivo" )
@NamedQueries({
    @NamedQuery(name="SRIArchivo.GetAll",query="SELECT p FROM SRIArchivo p"),
    @NamedQuery(name="SRIArchivo.GetById",query="SELECT p FROM SRIArchivo p WHERE p.NIdArchivo = :idEntidad")
})
public class SRIArchivo  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idarchivo" )
    private int NIdArchivo;
    
    
    @Column(name = "idplanificacion")
    private int NIdPlanificacion;

    
    @Column(name = "nombrearchivo")
    private String SNombreArchivo;

    
    @Column(name = "archivo")
    private Blob BlobArchivo;

    public int getNIdArchivo() {
        return NIdArchivo;
    }

    public void setNIdArchivo(int NIdArchivo) {
        this.NIdArchivo = NIdArchivo;
    }

    public int getNIdPlanificacion() {
        return NIdPlanificacion;
    }

    public void setNIdPlanificacion(int NIdPlanificacion) {
        this.NIdPlanificacion = NIdPlanificacion;
    }

    public String getSNombreArchivo() {
        return SNombreArchivo;
    }

    public void setSNombreArchivo(String SNombreArchivo) {
        this.SNombreArchivo = SNombreArchivo;
    }

    public Blob getBlobArchivo() {
        return BlobArchivo;
    }

    public void setBlobArchivo(Blob BlobArchivo) {
        this.BlobArchivo = BlobArchivo;
    }
    







    

    
    

}

