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
@Table(name = "estado" )
@NamedQueries({
    @NamedQuery(name="SRIEstado.GetAll",query="SELECT p FROM SRIEstado p"),
    @NamedQuery(name="SRIEstado.GetById",query="SELECT p FROM SRIEstado p WHERE p.NIdEstado = :idEntidad")
})
public class SRIEstado  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idestado" )
    private int NIdEstado;
    
    @Column(name = "nombreestado")
    private String SNombreEstado;



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
    

    


  



    

    
    

}

