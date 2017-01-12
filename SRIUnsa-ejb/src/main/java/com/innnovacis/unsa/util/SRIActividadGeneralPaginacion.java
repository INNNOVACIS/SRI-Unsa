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
public class SRIActividadGeneralPaginacion implements Serializable {
    
    @Id
    @Column(name = "idactividadinvestigacion" )
    private int idactividadinvestigacion;
    
    @Column(name = "id" )
    private int id;

 
    @Column(name = "nombreactividad" )
    private String nombreactividad;
    
    
    @Column(name = "facultad" )
    private String facultad;

    /////
    
    
    @Column(name = "departamento" )
    private String departamento;
    
    
    @Column(name = "escuela" )
    private String escuela;
    
    
    
    @Column(name = "semestre" )
    private String semestre;
    
    @Column(name = "fondoconcursable" )
    private String fondoconcursable;
    
    
    @Column(name = "tipoactividad" )
    private String tipoactividad;
    
    @Column(name = "horas" )
    private int horas;
    
    
    @Column(name = "fechacreacion" )
    private String fechacreacion;
    
    
    @Column(name = "ultimafecha" )
    private String ultimafecha;
    
     @Column(name = "ultimoaprobador" )
    private String ultimoaprobador;
     
      @Column(name = "pendiente" )
    private String pendiente;

    

    @Column(name = "cantidad" )
    private int cantidad;

    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
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
    
    
    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getUltimafecha() {
        return ultimafecha;
    }

    public void setUltimafecha(String ultimafecha) {
        this.ultimafecha = ultimafecha;
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
    
    public ArrayList<String> getArrayDatos() {
        ArrayList<String> arrayRpta = new ArrayList<String>();
        arrayRpta.add(this.getFacultad());
        arrayRpta.add(this.getDepartamento());
        arrayRpta.add(this.getSemestre());
        arrayRpta.add(this.getNombreactividad());
        arrayRpta.add(this.getTipoactividad());
        arrayRpta.add(this.getFechacreacion());
        arrayRpta.add(this.getUltimafecha());
        arrayRpta.add(this.getUltimoaprobador());
        arrayRpta.add(this.getPendiente());
        arrayRpta.add(this.getFondoconcursable());
        
        return arrayRpta;
    }
    
}
