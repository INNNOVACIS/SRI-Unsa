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
@Table(name = "categoriadocente" )
@NamedQueries({
    @NamedQuery(name="SRICategoriaDocente.GetAll",query="SELECT p FROM SRICategoriaDocente p"),
    @NamedQuery(name="SRICategoriaDocente.GetById",query="SELECT p FROM SRICategoriaDocente p WHERE p.NIdCategoriaDocente = :idEntidad")
})
public class SRICategoriaDocente  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idcategoriadocente" )
    private int NIdCategoriaDocente;
    
    @Column(name = "descripcion")
    private String SDescripcion;

    public int getNIdCategoriaDocente() {
        return NIdCategoriaDocente;
    }

    public void setNIdCategoriaDocente(int NIdCategoriaDocente) {
        this.NIdCategoriaDocente = NIdCategoriaDocente;
    }
    
    public String getSDescripcion() {
        return SDescripcion;
    }

    public void setSDescripcion(String SDescripcion) {
        this.SDescripcion = SDescripcion;
    }
    
}

