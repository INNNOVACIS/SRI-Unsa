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
@Table(name = "usuariocategoriadocente" )
@NamedQueries({
    @NamedQuery(name="SRIUsuarioCategoriaDocente.GetAll",query="SELECT p FROM SRIUsuarioCategoriaDocente p"),
    @NamedQuery(name="SRIUsuarioCategoriaDocente.GetById",query="SELECT p FROM SRIUsuarioCategoriaDocente p WHERE p.NIdUsuarioCategoriaDocente = :idEntidad")
})
public class SRIUsuarioCategoriaDocente  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idusuariocategoriadocente" )
    private int NIdUsuarioCategoriaDocente;
    
    @Column(name = "idcategoriadocente" )
    private int NIdCategoriaDocente;
    
    @Column(name = "idusuario" )
    private int NIdUsuario;

    public int getNIdUsuarioCategoriaDocente() {
        return NIdUsuarioCategoriaDocente;
    }

    public void setNIdUsuarioCategoriaDocente(int NIdUsuarioCategoriaDocente) {
        this.NIdUsuarioCategoriaDocente = NIdUsuarioCategoriaDocente;
    }

    public int getNIdCategoriaDocente() {
        return NIdCategoriaDocente;
    }

    public void setNIdCategoriaDocente(int NIdCategoriaDocente) {
        this.NIdCategoriaDocente = NIdCategoriaDocente;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }
    
    
}

