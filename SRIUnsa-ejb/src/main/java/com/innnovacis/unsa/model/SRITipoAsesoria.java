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
@Table(name = "tipoasesoria" )
@NamedQueries({
    @NamedQuery(name="SRITipoAsesoria.GetAll",query="SELECT p FROM SRITipoAsesoria p"),
    @NamedQuery(name="SRITipoAsesoria.GetById",query="SELECT p FROM SRITipoAsesoria p WHERE p.NIdTipoAsesoria = :idEntidad")
})
public class SRITipoAsesoria  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idtipoasesoria" )
    private int NIdTipoAsesoria;
    @Column(name = "nombretipoasesoria")
    private String SNombreTipoAsesoria;

    
    public int getNIdTipoAsesoria() {
        return NIdTipoAsesoria;
    }

    public void setNIdTipoAsesoria(int NIdTipoAsesoria) {
        this.NIdTipoAsesoria = NIdTipoAsesoria;
    }

    public String getSNombreTipoAsesoria() {
        return SNombreTipoAsesoria;
    }

    public void setSNombreTipoAsesoria(String SNombreTipoAsesoria) {
        this.SNombreTipoAsesoria = SNombreTipoAsesoria;
    }


    

    
    

}

