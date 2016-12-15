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
@Table(name = "detallemasiva" )
@NamedQueries({
    @NamedQuery(name="SRIDetalleMasiva.GetAll",query="SELECT p FROM SRIDetalleMasiva p"),
    @NamedQuery(name="SRIDetalleMasiva.GetById",query="SELECT p FROM SRIDetalleMasiva p WHERE p.NIdDetalleMasiva = :idEntidad")
})
public class SRIDetalleMasiva  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "iddetallemasiva" )
    private int NIdDetalleMasiva;
    
    @Column(name = "idcabeceramasiva" )
    private int NIdCabeceraMasiva;
    
    @Column(name = "iddetalleinvestigacionflujo" )
    private int NIdDetalleInvestigacionFlujo;
    
    @Column(name = "idactividadinvestigacion" )
    private int NIdActividadInvestigacion;

    public int getNIdDetalleMasiva() {
        return NIdDetalleMasiva;
    }

    public void setNIdDetalleMasiva(int NIdDetalleMasiva) {
        this.NIdDetalleMasiva = NIdDetalleMasiva;
    }

    public int getNIdCabeceraMasiva() {
        return NIdCabeceraMasiva;
    }

    public void setNIdCabeceraMasiva(int NIdCabeceraMasiva) {
        this.NIdCabeceraMasiva = NIdCabeceraMasiva;
    }

    public int getNIdDetalleInvestigacionFlujo() {
        return NIdDetalleInvestigacionFlujo;
    }

    public void setNIdDetalleInvestigacionFlujo(int NIdDetalleInvestigacionFlujo) {
        this.NIdDetalleInvestigacionFlujo = NIdDetalleInvestigacionFlujo;
    }

    public int getNIdActividadInvestigacion() {
        return NIdActividadInvestigacion;
    }

    public void setNIdActividadInvestigacion(int NIdActividadInvestigacion) {
        this.NIdActividadInvestigacion = NIdActividadInvestigacion;
    }
    
    
}

