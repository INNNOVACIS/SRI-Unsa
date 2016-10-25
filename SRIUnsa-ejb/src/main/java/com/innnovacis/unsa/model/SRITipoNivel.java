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
@Table(name = "tiponivel" )
@NamedQueries({
    @NamedQuery(name="SRITipoNivel.GetAll",query="SELECT p FROM SRITipoNivel p"),
    @NamedQuery(name="SRITipoNivel.GetById",query="SELECT p FROM SRITipoNivel p WHERE p.NIdTipoNivel = :idEntidad")
})
public class SRITipoNivel  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idtiponivel" )
    private int NIdTipoNivel;

    @Column(name = "nombretiponivel")
    private String SNombreTipoNivel;

    
    public int getNIdTipoNivel() {
        return NIdTipoNivel;
    }

    public void setNIdTipoNivel(int NIdTipoNivel) {
        this.NIdTipoNivel = NIdTipoNivel;
    }

    public String getSNombreTipoNivel() {
        return SNombreTipoNivel;
    }

    public void setSNombreTipoNivel(String SNombreTipoNivel) {
        this.SNombreTipoNivel = SNombreTipoNivel;
    }


  


    
    

}

