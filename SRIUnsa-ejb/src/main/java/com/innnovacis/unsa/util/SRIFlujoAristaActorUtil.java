/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author innnovacis
 */
@Entity
@Immutable
@Subselect("")
public class SRIFlujoAristaActorUtil implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idarista" )
    private int NIdArista;
    
    @Column(name = "flujo")
    private String SFlujo;

    @Column(name = "idActorDestino")
    private int NIdActorDestino;
    
    @Column(name = "idActorOrigen")
    private int NIdActorOrigen;
    
    @Column(name = "idEstado")
    private int NIdEstado;
    
    @Column(name = "nombreestado" )
    private String SNombreEstado;
    
    @Column(name = "nombrearista" )
    private String SNombreArista;

    @Column(name = "actorOrigen")
    private String SActorOrigen;
    
    @Column(name = "actorDestino" )
    private String SActorDestino;

    public int getNIdArista() {
        return NIdArista;
    }

    public int getNIdEstado() {
        return NIdEstado;
    }

    public void setNIdEstado(int NIdEstado) {
        this.NIdEstado = NIdEstado;
    }

    public String getSNombreEstado() {
        return SNombreEstado;
    }

    public void setSNombreEstado(String SNombreEstado) {
        this.SNombreEstado = SNombreEstado;
    }

    public void setNIdArista(int NIdArista) {
        this.NIdArista = NIdArista;
    }

    public String getSFlujo() {
        return SFlujo;
    }

    public void setSFlujo(String SFlujo) {
        this.SFlujo = SFlujo;
    }

    public int getNIdActorDestino() {
        return NIdActorDestino;
    }

    public void setNIdActorDestino(int NIdActorDestino) {
        this.NIdActorDestino = NIdActorDestino;
    }

    public int getNIdActorOrigen() {
        return NIdActorOrigen;
    }

    public void setNIdActorOrigen(int NIdActorOrigen) {
        this.NIdActorOrigen = NIdActorOrigen;
    }

    public String getSNombreArista() {
        return SNombreArista;
    }

    public void setSNombreArista(String SNombreArista) {
        this.SNombreArista = SNombreArista;
    }

    public String getSActorOrigen() {
        return SActorOrigen;
    }

    public void setSActorOrigen(String SActorOrigen) {
        this.SActorOrigen = SActorOrigen;
    }

    public String getSActorDestino() {
        return SActorDestino;
    }

    public void setSActorDestino(String SActorDestino) {
        this.SActorDestino = SActorDestino;
    }
    
}
