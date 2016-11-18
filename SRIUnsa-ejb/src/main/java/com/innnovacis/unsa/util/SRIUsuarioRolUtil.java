/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author innnovacis
 */
@Entity
@Immutable
@Subselect("")
public class SRIUsuarioRolUtil implements Serializable{
    
    @Id
    @Column(name = "idUsuarioRol")
    private int NIdUsuarioRol;
    
    @Column(name = "idUsuario" )
    private int idUsuario;
    
    @Column(name = "idRol" )
    private int idRol;
    
    @Column(name = "nombreUsuario" )
    private String nombreUsuario;
    
    @Column(name = "nombreRol" )
    private String nombreRol;

    public int getNIdUsuarioRol() {
        return NIdUsuarioRol;
    }

    public void setNIdUsuarioRol(int NIdUsuarioRol) {
        this.NIdUsuarioRol = NIdUsuarioRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
