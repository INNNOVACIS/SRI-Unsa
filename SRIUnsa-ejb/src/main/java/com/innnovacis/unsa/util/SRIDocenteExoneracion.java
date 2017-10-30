/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIEntidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class SRIDocenteExoneracion extends SRIEntidad implements Serializable{
    
  
    @Column(name = "idusuario" )
    private int NIdUsuario;
    
    @Column(name = "idpersona" )
    private int NIdPersona;
    
    @Column(name = "idexoneracion" )
    private int NIdExoneracion;
    
    @Column(name = "idsemestre" )
    private int NIdSemestre;
      @Id
    @GeneratedValue
    @Column(name = "idusuarioexoneracion" )
    private int NIdUsuarioExoneracion;

    @Column(name = "nombresemestre")
    private String SSemestre;
    
    @Column(name = "email")
    private String SEmail;
    
    @Column(name = "nombre")
    private String SNombre;
    
    @Column(name = "apellido")
    private String SApellido;
    
    @Column(name = "nombreexoneracion")
    private String SNombreExoneracion;
    
    @Column(name = "descripcion")
    private String SDescripcion;

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }

    public int getNIdPersona() {
        return NIdPersona;
    }

    public void setNIdPersona(int NIdPersona) {
        this.NIdPersona = NIdPersona;
    }

    public int getNIdExoneracion() {
        return NIdExoneracion;
    }

    public void setNIdExoneracion(int NIdExoneracion) {
        this.NIdExoneracion = NIdExoneracion;
    }

    public int getNIdUsuarioExoneracion() {
        return NIdUsuarioExoneracion;
    }

    public void setNIdUsuarioExoneracion(int NIdUsuarioExoneracion) {
        this.NIdUsuarioExoneracion = NIdUsuarioExoneracion;
    }

    public int getNIdSemestre() {
        return NIdSemestre;
    }

    public void setNIdSemestre(int NIdSemestre) {
        this.NIdSemestre = NIdSemestre;
    }

    public String getSSemestre() {
        return SSemestre;
    }

    public void setSSemestre(String SSemestre) {
        this.SSemestre = SSemestre;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }

    public String getSNombre() {
        return SNombre;
    }

    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }

    public String getSApellido() {
        return SApellido;
    }

    public void setSApellido(String SApellido) {
        this.SApellido = SApellido;
    }

    public String getSNombreExoneracion() {
        return SNombreExoneracion;
    }

    public void setSNombreExoneracion(String SNombreExoneracion) {
        this.SNombreExoneracion = SNombreExoneracion;
    }

    public String getSDescripcion() {
        return SDescripcion;
    }

    public void setSDescripcion(String SDescripcion) {
        this.SDescripcion = SDescripcion;
    }

    
    
}
