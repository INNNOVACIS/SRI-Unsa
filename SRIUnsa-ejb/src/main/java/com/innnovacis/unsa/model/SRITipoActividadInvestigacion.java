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
@Table(name = "tipoactividadinvestigacion" )
@NamedQueries({
    @NamedQuery(name="SRITipoActividadInvestigacion.GetAll",query="SELECT p FROM SRITipoActividadInvestigacion p"),
    @NamedQuery(name="SRITipoActividadInvestigacion.GetById",query="SELECT p FROM SRITipoActividadInvestigacion p WHERE p.NIdTipoActividadInvestigacion = :idEntidad")
})
public class SRITipoActividadInvestigacion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idtipoactividadinvestigacion" )
    private int NIdTipoActividadInvestigacion;
    
    @Column(name = "nombreactividadinvestigacion")
    private String SNombreActividadInvestigacion;


    public int getNIdTipoActividadInvestigacion() {
        return NIdTipoActividadInvestigacion;
    }

    public void setNIdTipoActividadInvestigacion(int NIdTipoActividadInvestigacion) {
        this.NIdTipoActividadInvestigacion = NIdTipoActividadInvestigacion;
    }

    public String getSNombreActividadInvestigacion() {
        return SNombreActividadInvestigacion;
    }

    public void setSNombreActividadInvestigacion(String SNombreActividadInvestigacion) {
        this.SNombreActividadInvestigacion = SNombreActividadInvestigacion;
    }
    

   
    





    

    
    

}

