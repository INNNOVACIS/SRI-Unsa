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
@Table(name = "rol" )
@NamedQueries({
    @NamedQuery(name="SRIRol.GetAll",query="SELECT p FROM SRIRol p"),
    @NamedQuery(name="SRIRol.GetById",query="SELECT p FROM SRIRol p WHERE p.NIdRol = :idEntidad")
})
public class SRIRol  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idrol" )
    private int NIdRol;
    
    @Column(name = "nombreRol")
    private String SNombreRol;


    public int getNIdRol() {
        return NIdRol;
    }

    public void setNIdRol(int NIdRol) {
        this.NIdRol = NIdRol;
    }

    public String getSNombreRol() {
        return SNombreRol;
    }

    public void setSNombreRol(String SNombreRol) {
        this.SNombreRol = SNombreRol;
    }


  



    

    
    

}

