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
public class SRIUsuarioColor implements Serializable{
    
    @Id
    @Column(name = "idpersona")
    private int NIdPersona;
    
    @Column(name = "idusuario")
    private int NIdUsuario;
    
    @Column(name = "dni" )
    private int NDni;
    
    @Column(name = "nombre" )
    private String SNombre;
    
    @Column(name = "apellido" )
    private String SApellido;
    
    @Column(name = "email" )
    private String SEmail;
    
    @Column(name = "color" )
    private int NColor;
    
    @Column(name = "nombreactividadinvestigacion" )
    private String SNombreActividadInvestigacion;

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

    public int getNDni() {
        return NDni;
    }

    public void setNDni(int NDni) {
        this.NDni = NDni;
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

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }

    public int getNColor() {
        return NColor;
    }

    public void setNColor(int NColor) {
        this.NColor = NColor;
    }

    public String getSNombreActividadInvestigacion() {
        return SNombreActividadInvestigacion;
    }

    public void setSNombreActividadInvestigacion(String SNombreActividadInvestigacion) {
        this.SNombreActividadInvestigacion = SNombreActividadInvestigacion;
    }

}
