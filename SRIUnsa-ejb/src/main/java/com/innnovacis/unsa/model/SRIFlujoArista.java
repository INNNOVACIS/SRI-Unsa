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
@Table(name = "flujoarista" )
@NamedQueries({
    @NamedQuery(name="SRIFlujoArista.GetAll",query="SELECT p FROM SRIFlujoArista p"),
    @NamedQuery(name="SRIFlujoArista.GetById",query="SELECT p FROM SRIFlujoArista p WHERE p.NIdArista = :idEntidad")
})
public class SRIFlujoArista  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idarista" )
    private int NIdArista;

    @Column(name = "idflujoactordestino")
    private int SIdFlujoActorDestino;

    @Column(name = "idflujoactororigen")
    private int SIdFlujoActorOrigen;
 
    @Column(name = "nombrearista")
    private String SNombreArista;
    
    @Column(name = "flujo")
    private String SFlujo;


    
    
    public int getNIdArista() {
        return NIdArista;
    }

    public void setNIdArista(int NIdArista) {
        this.NIdArista = NIdArista;
    }

    public int getSIdFlujoActorDestino() {
        return SIdFlujoActorDestino;
    }

    public void setSIdFlujoActorDestino(int SIdFlujoActorDestino) {
        this.SIdFlujoActorDestino = SIdFlujoActorDestino;
    }

    public int getSIdFlujoActorOrigen() {
        return SIdFlujoActorOrigen;
    }

    public void setSIdFlujoActorOrigen(int SIdFlujoActorOrigen) {
        this.SIdFlujoActorOrigen = SIdFlujoActorOrigen;
    }

    public String getSNombreArista() {
        return SNombreArista;
    }

    public void setSNombreArista(String SNombreArista) {
        this.SNombreArista = SNombreArista;
    }

    public String getSFlujo() {
        return SFlujo;
    }

    public void setSFlujo(String SFlujo) {
        this.SFlujo = SFlujo;
    }
    

  

  



    

    
    

}

