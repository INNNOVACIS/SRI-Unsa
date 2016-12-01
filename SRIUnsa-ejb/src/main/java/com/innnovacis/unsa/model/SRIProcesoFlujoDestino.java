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
@Table(name = "procesoflujodestino" )
@NamedQueries({
    @NamedQuery(name="SRIProcesoFlujoDestino.GetByIdUsuarioFlujoAndEstadoEnvio",query="SELECT p FROM SRIProcesoFlujoDestino p WHERE p.NIdUsuarioFlujo = :idUsuarioFlujo and p.SEstadoEnvio = :estadoEnvio"),
    @NamedQuery(name="SRIProcesoFlujoDestino.GetById",query="SELECT p FROM SRIProcesoFlujoDestino p WHERE p.NIdProcesoFlujoDestino = :idEntidad")
})
public class SRIProcesoFlujoDestino  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idprocesoflujodestino" )
    private int NIdProcesoFlujoDestino;

    
    @Column(name = "idprocesoflujo")
    private int NIdProcesoFlujo;

    
    @Column(name = "idusuarioflujo")
    private int NIdUsuarioFlujo;

    @Column(name = "estadoenvio")
    private String SEstadoEnvio;

    
    public int getNIdProcesoFlujoDestino() {
        return NIdProcesoFlujoDestino;
    }

    public void setNIdProcesoFlujoDestino(int NIdProcesoFlujoDestino) {
        this.NIdProcesoFlujoDestino = NIdProcesoFlujoDestino;
    }

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

    public String getSEstadoEnvio() {
        return SEstadoEnvio;
    }

    public void setSEstadoEnvio(String SEstadoEnvio) {
        this.SEstadoEnvio = SEstadoEnvio;
    }
    







    

    
    

}

