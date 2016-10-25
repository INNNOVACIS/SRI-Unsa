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
@Table(name = "procesoflujo" )
@NamedQueries({
    @NamedQuery(name="SRIProcesoFlujo.GetAll",query="SELECT p FROM SRIProcesoFlujo p"),
    @NamedQuery(name="SRIProcesoFlujo.GetById",query="SELECT p FROM SRIProcesoFlujo p WHERE p.NIdProcesoFlujo = :idEntidad")
})
public class SRIProcesoFlujo  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idprocesoflujo" )
    private int NIdProcesoFlujo;


    @Column(name = "idusuarioflujo")
    private int NIdUsuarioFlujo;

    
    @Column(name = "flujo")
    private String SFlujo;
    
    
    public int getNIdProcesoFlujo() {
        return NIdProcesoFlujo;
    }

    public void setNIdProcesoFlujo(int NIdProcesoFlujo) {
        this.NIdProcesoFlujo = NIdProcesoFlujo;
    }

    public int getNIdUsuarioFlujo() {
        return NIdUsuarioFlujo;
    }

    public void setNIdUsuarioFlujo(int NIdUsuarioFlujo) {
        this.NIdUsuarioFlujo = NIdUsuarioFlujo;
    }

    public String getSFlujo() {
        return SFlujo;
    }

    public void setSFlujo(String SFlujo) {
        this.SFlujo = SFlujo;
    }
    

    








    

    
    

}

