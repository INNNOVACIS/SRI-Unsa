/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author will
 */
@Entity
@Immutable
@Subselect("")
public class SRIDocentesActividades implements Serializable {
    
    
    @Column(name = "idactividadinvestigacion" )
    private int idactividadinvestigacion;
    
    @Column(name = "nombreactividadinvestigacion" )
    private String nombreactividad;
 
    @Column(name = "facultad")
    private String facultad;
    
    @Column(name = "departamento")
    private String departamento;
    
    @Column(name = "semestre")
    private String semestre;
    
    @Id
    @Column(name = "idpersona")
    private int idpersona;
    
    @Column(name = "nombre")
    private String nombres;
    
    @Column(name = "apellido")
    private String apellidos;
    
    @Column(name = "cantidad")
    private int cantidad;

    public int getIdactividadinvestigacion() {
        return idactividadinvestigacion;
    }

    public void setIdactividadinvestigacion(int idactividadinvestigacion) {
        this.idactividadinvestigacion = idactividadinvestigacion;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
