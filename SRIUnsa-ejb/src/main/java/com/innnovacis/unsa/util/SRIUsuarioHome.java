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
public class SRIUsuarioHome implements Serializable{
    
    @Id
    @Column(name = "idusuario")
    private int idUsuario;
    
    @Column(name = "idpersona")
    private int idpersona;
    
    @Column(name = "idusuariodirector")
    private int idUsuarioDirector;
    
    @Column(name = "idpersonadirector")
    private int idPersonaDirector;
    
    @Column(name = "idfacultad")
    private int idFacultad;
    
    @Column(name = "iddepartamento")
    private int idDepartamento;
    
    @Column(name = "nombrefacultad")
    private String nombreFacultad;
    
    @Column(name = "nombreDepartamento")
    private String nombreDepartamento;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "nombredirector")
    private String nombreDirector;
    
    @Column(name = "apellidodirector")
    private String apellidoDirector;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getIdUsuarioDirector() {
        return idUsuarioDirector;
    }

    public void setIdUsuarioDirector(int idUsuarioDirector) {
        this.idUsuarioDirector = idUsuarioDirector;
    }

    public int getIdPersonaDirector() {
        return idPersonaDirector;
    }

    public void setIdPersonaDirector(int idPersonaDirector) {
        this.idPersonaDirector = idPersonaDirector;
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

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
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

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getApellidoDirector() {
        return apellidoDirector;
    }

    public void setApellidoDirector(String apellidoDirector) {
        this.apellidoDirector = apellidoDirector;
    }
    
    
}
