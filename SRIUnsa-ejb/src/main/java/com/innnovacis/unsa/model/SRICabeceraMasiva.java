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
@Table(name = "cabeceramasiva" )
@NamedQueries({
    @NamedQuery(name="SRICabeceraMasiva.GetAll",query="SELECT p FROM SRICabeceraMasiva p"),
    @NamedQuery(name="SRICabeceraMasiva.GetById",query="SELECT p FROM SRICabeceraMasiva p WHERE p.NIdCabeceraMasiva = :idEntidad")
})
public class SRICabeceraMasiva  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idcabeceramasiva" )
    private int NIdCabeceraMasiva;
    
    @Column(name = "idusuarioflujo" )
    private int NIdUsuarioFlujo;
    
    @Column(name = "flujo")
    private String flujo;

    public int getNIdCabeceraMasiva() {
        return NIdCabeceraMasiva;
    }

    public void setNIdCabeceraMasiva(int NIdCabeceraMasiva) {
        this.NIdCabeceraMasiva = NIdCabeceraMasiva;
    }

    public int getNIdUsuarioFlujo() {
        return NIdUsuarioFlujo;
    }

    public void setNIdUsuarioFlujo(int NIdUsuarioFlujo) {
        this.NIdUsuarioFlujo = NIdUsuarioFlujo;
    }

    public String getFlujo() {
        return flujo;
    }

    public void setFlujo(String flujo) {
        this.flujo = flujo;
    }
    
    
}

