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
public class SRIUsuarioLogin implements Serializable{
    
    @Column(name = "idUsuarioRol")
    private int NIdUsuarioRol;
    
    @Id
    @Column(name = "idUsuario" )
    private int idUsuario;
    
    @Column(name = "idRol" )
    private int idRol;
    
    @Column(name = "idFacultad" )
    private int idFacultad;
    
    @Column(name = "idDepartamento" )
    private int idDepartamento;
    
    @Column(name = "idPersona" )
    private int idPersona;
    
    @Column(name = "nombreUsuario" )
    private String nombreUsuario;
    
    @Column(name = "nombreRol" )
    private String nombreRol;
    
    @Column(name = "nombre" )
    private String nombre;
    
    @Column(name = "apellido" )
    private String apellido;
    
    @Column(name = "codigo" )
    private String codigo;
    
    @Column(name = "facultad" )
    private String facultad;

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
