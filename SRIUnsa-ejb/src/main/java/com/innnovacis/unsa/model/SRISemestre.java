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
@Table(name = "semestre" )
@NamedQueries({
    @NamedQuery(name="SRISemestre.GetAll",query="SELECT p FROM SRISemestre p"),
    @NamedQuery(name="SRISemestre.GetById",query="SELECT p FROM SRISemestre p WHERE p.NIdSemestre = :idEntidad")
})
public class SRISemestre  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idsemestre" )
    private int NIdSemestre;

    
    @Column(name = "nombresemestre")
    private String SNombreSemestre;

    
    public int getNIdSemestre() {
        return NIdSemestre;
    }

    public void setNIdSemestre(int NIdSemestre) {
        this.NIdSemestre = NIdSemestre;
    }

    public String getSNombreSemestre() {
        return SNombreSemestre;
    }

    public void setSNombreSemestre(String SNombreSemestre) {
        this.SNombreSemestre = SNombreSemestre;
    }

 


    
    

}

