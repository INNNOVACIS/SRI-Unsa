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
@Table(name = "usuarioexoneracion" )
@NamedQueries({
    @NamedQuery(name="SRIUsuarioExoneracion.GetAll",query="SELECT p FROM SRIUsuarioExoneracion p"),
    @NamedQuery(name="SRIUsuarioExoneracion.GetById",query="SELECT p FROM SRIUsuarioExoneracion p WHERE p.NIdUsuarioExoneracion = :idEntidad"),
    @NamedQuery(name="SRIUsuarioExoneracion.GetByIdUsuarioIdSemestre",
                query="SELECT p FROM SRIUsuarioExoneracion p WHERE p.NIdUsuario = :idUsuario and p.NIdSemestre = :idSemestre and p.SEstado = 'A'")
})
public class SRIUsuarioExoneracion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idusuarioexoneracion" )
    private int NIdUsuarioExoneracion;
    
    @Column(name = "idexoneracion" )
    private int NIdExoneracion;
    
    @Column(name = "idusuario" )
    private int NIdUsuario;
    
    @Column(name = "idsemestre" )
    private int NIdSemestre;

    public int getNIdUsuarioExoneracion() {
        return NIdUsuarioExoneracion;
    }

    public void setNIdUsuarioExoneracion(int NIdUsuarioExoneracion) {
        this.NIdUsuarioExoneracion = NIdUsuarioExoneracion;
    }

    public int getNIdExoneracion() {
        return NIdExoneracion;
    }

    public void setNIdExoneracion(int NIdExoneracion) {
        this.NIdExoneracion = NIdExoneracion;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }

    public int getNIdSemestre() {
        return NIdSemestre;
    }

    public void setNIdSemestre(int NIdSemestre) {
        this.NIdSemestre = NIdSemestre;
    }

    
}

