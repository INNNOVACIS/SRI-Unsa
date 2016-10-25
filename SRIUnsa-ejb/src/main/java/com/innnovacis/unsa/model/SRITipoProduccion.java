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
@Table(name = "tipoproduccion" )
@NamedQueries({
    @NamedQuery(name="SRITipoProduccion.GetAll",query="SELECT p FROM SRITipoProduccion p"),
    @NamedQuery(name="SRITipoProduccion.GetById",query="SELECT p FROM SRITipoProduccion p WHERE p.NIdTipoProduccion = :idEntidad")
})
public class SRITipoProduccion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idtipoproduccion" )
    private int NIdTipoProduccion;

    @Column(name = "nombretipoproduccion")
    private String SNombreTipoProduccion;

    public int getNIdTipoProduccion() {
        return NIdTipoProduccion;
    }

    public void setNIdTipoProduccion(int NIdTipoProduccion) {
        this.NIdTipoProduccion = NIdTipoProduccion;
    }

    public String getSNombreTipoProduccion() {
        return SNombreTipoProduccion;
    }

    public void setSNombreTipoProduccion(String SNombreTipoProduccion) {
        this.SNombreTipoProduccion = SNombreTipoProduccion;
    }


    
    

}

