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
@Table(name = "fondoconcursable" )
@NamedQueries({
    @NamedQuery(name="SRIFondoConcursable.GetAll",query="SELECT p FROM SRIFondoConcursable p"),
    @NamedQuery(name="SRIFondoConcursable.GetById",query="SELECT p FROM SRIFondoConcursable p WHERE p.NIdFondoConcursable = :idEntidad")
})
public class SRIFondoConcursable  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idfondoconcursable" )
    private int NIdFondoConcursable;

    @Column(name = "nombrefondoconcursable")
    private String SNombreFondoConcursable;

    
    public int getNIdFondoConcursable() {
        return NIdFondoConcursable;
    }

    public void setNIdFondoConcursable(int NIdFondoConcursable) {
        this.NIdFondoConcursable = NIdFondoConcursable;
    }

    public String getSNombreFondoConcursable() {
        return SNombreFondoConcursable;
    }

    public void setSNombreFondoConcursable(String SNombreFondoConcursable) {
        this.SNombreFondoConcursable = SNombreFondoConcursable;
    }

   

    
    

}

