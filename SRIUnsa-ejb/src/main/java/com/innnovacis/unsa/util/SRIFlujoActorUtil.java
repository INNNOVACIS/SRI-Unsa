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
public class SRIFlujoActorUtil implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idflujoactor" )
    private int NIdFlujoActor;
    
    @Column(name = "nombreactor")
    private String SNombreActor;

    @Column(name = "flujo")
    private String SFlujo;
    
    @Column(name = "codigo")
    private String SCodigo;
    
    @Column(name = "idusuarioflujo" )
    private int NIdUsuarioFlujo;

    public int getNIdUsuarioFlujo() {
        return NIdUsuarioFlujo;
    }

    public void setNIdUsuarioFlujo(int NIdUsuarioFlujo) {
        this.NIdUsuarioFlujo = NIdUsuarioFlujo;
    }

    public String getSCodigo() {
        return SCodigo;
    }

    public void setSCodigo(String SCodigo) {
        this.SCodigo = SCodigo;
    }

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
