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
@Table(name = "plantilladocumentoactividad" )
@NamedQueries({
    @NamedQuery(name="SRIPlantillaDocumentoActividad.GetAll",query="SELECT p FROM SRIPlantillaDocumentoActividad p"),
    @NamedQuery(name="SRIPlantillaDocumentoActividad.GetById",query="SELECT p FROM SRIPlantillaDocumentoActividad p WHERE p.NIdPlantillaDocumentoActividad = :idEntidad")
})
public class SRIPlantillaDocumentoActividad  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idplantilladocumentoactividad" )
    private int NIdPlantillaDocumentoActividad;
    
    @Column(name = "idactividadinvestigacion" )
    private int NIdActividadInvestigacion;
    
    @Column(name = "idplantilladocumento" )
    private int NIdPlantillaDocumento;
    
    @Column(name = "valor" )
    private String SValor;

    public int getNIdPlantillaDocumentoActividad() {
        return NIdPlantillaDocumentoActividad;
    }

    public void setNIdPlantillaDocumentoActividad(int NIdPlantillaDocumentoActividad) {
        this.NIdPlantillaDocumentoActividad = NIdPlantillaDocumentoActividad;
    }

    public int getNIdActividadInvestigacion() {
        return NIdActividadInvestigacion;
    }

    public void setNIdActividadInvestigacion(int NIdActividadInvestigacion) {
        this.NIdActividadInvestigacion = NIdActividadInvestigacion;
    }

    public int getNIdPlantillaDocumento() {
        return NIdPlantillaDocumento;
    }

    public void setNIdPlantillaDocumento(int NIdPlantillaDocumento) {
        this.NIdPlantillaDocumento = NIdPlantillaDocumento;
    }

    public String getSValor() {
        return SValor;
    }

    public void setSValor(String SValor) {
        this.SValor = SValor;
    }
    
}

