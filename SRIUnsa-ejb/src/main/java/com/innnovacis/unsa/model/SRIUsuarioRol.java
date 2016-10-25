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
@Table(name = "usuariorol" )
@NamedQueries({
    @NamedQuery(name="SRIUsuarioRol.GetAll",query="SELECT p FROM SRIUsuarioRol p"),
    @NamedQuery(name="SRIUsuarioRol.GetById",query="SELECT p FROM SRIUsuarioRol p WHERE p.NIdUsuarioRol = :idEntidad")
})
public class SRIUsuarioRol  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idusuariorol" )
    private int NIdUsuarioRol;

    @Column(name = "idusuario")
    private int NIdUsuario;

    
    @Column(name = "idrol")
    private int NIdRol;
  
    
    
    public int getNIdUsuarioRol() {
        return NIdUsuarioRol;
    }

    public void setNIdUsuarioRol(int NIdUsuarioRol) {
        this.NIdUsuarioRol = NIdUsuarioRol;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }

    public int getNIdRol() {
        return NIdRol;
    }

    public void setNIdRol(int NIdRol) {
        this.NIdRol = NIdRol;
    }






    

    
    

}

