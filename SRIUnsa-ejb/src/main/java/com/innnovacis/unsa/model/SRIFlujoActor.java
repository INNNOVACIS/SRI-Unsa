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
@Table(name = "flujoactor" )
@NamedQueries({
    @NamedQuery(name="SRIFlujoActor.GetAll",query="SELECT p FROM SRIFlujoActor p"),
    @NamedQuery(name="SRIFlujoActor.GetById",query="SELECT p FROM SRIFlujoActor p WHERE p.NIdFlujoActor = :idEntidad")
})
public class SRIFlujoActor  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idflujoactor" )
    private int NIdFlujoActor;
    
    @Column(name = "nombreactor")
    private String SNombreActor;

    @Column(name = "flujo")
    private String SFlujo;



    public int getNIdFlujoActor() {
        return NIdFlujoActor;
    }

    public void setNIdFlujoActor(int NIdFlujoActor) {
        this.NIdFlujoActor = NIdFlujoActor;
    }

    public String getSNombreActor() {
        return SNombreActor;
    }

    public void setSNombreActor(String SNombreActor) {
        this.SNombreActor = SNombreActor;
    }

    public String getSFlujo() {
        return SFlujo;
    }

    public void setSFlujo(String SFlujo) {
        this.SFlujo = SFlujo;
    }
    
  

  



    

    
    

}

