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
public class SRIEnviarInformeDepartamento implements Serializable {
    
    @Id
    @Column(name = "idactividadinvestigacion" )
    private int idactividadinvestigacion;
    
    @Column(name = "id" )
    private int id;

    @Column(name = "nombreactividad" )
    private String nombreactividad;
    
    @Column(name = "facultad" )
    private String facultad;

    @Column(name = "departamento" )
    private String departamento;
    
    @Column(name = "semestre" )
    private String semestre;
    
    @Column(name = "fondoconcursable" )
    private String fondoconcursable;
    
    @Column(name = "tipoactividad" )
    private String tipoactividad;
    
    @Column(name = "fechacreacion" )
    private String fechacreacion;
    
    @Column(name = "ultimoaprobador" )
    private String ultimoaprobador;
     
    @Column(name = "pendiente" )
    private String pendiente;

    @Column(name = "cantidad" )
    private int cantidad;

    @Column(name = "nombre" )
    private String nombre;
    
    @Column(name = "apellido" )
    private String apellido;
    
    @Column(name = "dni" )
    private String dni;

    public int getIdactividadinvestigacion() {
        return idactividadinvestigacion;
    }

    public void setIdactividadinvestigacion(int idactividadinvestigacion) {
        this.idactividadinvestigacion = idactividadinvestigacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFondoconcursable() {
        return fondoconcursable;
    }

    public void setFondoconcursable(String fondoconcursable) {
        this.fondoconcursable = fondoconcursable;
    }

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUltimoaprobador() {
        return ultimoaprobador;
    }

    public void setUltimoaprobador(String ultimoaprobador) {
        this.ultimoaprobador = ultimoaprobador;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public ArrayList<String> getArrayDatos() {
        ArrayList<String> arrayRpta = new ArrayList<String>();
//        arrayRpta.add(this.getFacultad());
        arrayRpta.add(this.getNombre() + " " + this.getApellido());
//        arrayRpta.add(this.getApellido());
        arrayRpta.add(this.getDni());
        arrayRpta.add(this.getDepartamento());
        arrayRpta.add(this.getNombreactividad());
        arrayRpta.add(this.getTipoactividad());
        arrayRpta.add(this.getSemestre());
        arrayRpta.add(this.getFechacreacion());
        arrayRpta.add(this.getUltimoaprobador());
        arrayRpta.add(this.getPendiente());
        arrayRpta.add(this.getFondoconcursable());
        
        return arrayRpta;
    }
    public static String[] getArrayHeaders() {
        String[] nombreColumnas = {"Nombre y Apellido", "DNI", "Departamento", "Nombre del proyecto",
            "Tipo de Actividad", "Semestre", "Fecha creación", "Última revisión", "Pendiente", "Fondo"};
        return nombreColumnas;
    }
    
}
