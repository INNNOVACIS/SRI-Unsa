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
    
    @Column(name = "idfacultad")
    private int NIdFacultad;
    
    @Column(name = "idtipoactividadinvestigacion")
    private int NIdTipoActividadInvestigacion;
    
    @Column(name = "dni" )
    private int NDni;
    
    @Column(name = "nombre" )
    private String SNombre;
    
    @Column(name = "apellido" )
    private String SApellido;
    
    @Column(name = "email" )
    private String SEmail;
    
    @Column(name = "facultad" )
    private String SFacultad;
    
    @Column(name = "departamento" )
    private String SDepartamento;
    
    @Column(name = "escuela" )
    private String SEscuela;
    
    @Column(name = "nombreactividadinvestigacion" )
    private String SNombreActividadInvestigacion;
    
    @Column(name = "color" )
    private int NColor;

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

    public int getNIdFacultad() {
        return NIdFacultad;
    }

    public void setNIdFacultad(int NIdFacultad) {
        this.NIdFacultad = NIdFacultad;
    }

    public int getNIdTipoActividadInvestigacion() {
        return NIdTipoActividadInvestigacion;
    }

    public void setNIdTipoActividadInvestigacion(int NIdTipoActividadInvestigacion) {
        this.NIdTipoActividadInvestigacion = NIdTipoActividadInvestigacion;
    }

    public String getSFacultad() {
        return SFacultad;
    }

    public void setSFacultad(String SFacultad) {
        this.SFacultad = SFacultad;
    }

    public String getSDepartamento() {
        return SDepartamento;
    }

    public void setSDepartamento(String SDepartamento) {
        this.SDepartamento = SDepartamento;
    }

    public String getSEscuela() {
        return SEscuela;
    }

    public void setSEscuela(String SEscuela) {
        this.SEscuela = SEscuela;
    }
    
    public ArrayList<String> getArrayDatos() {
        ArrayList<String> arrayRpta = new ArrayList<String>();
        arrayRpta.add(this.getSDepartamento());
        arrayRpta.add(this.getSNombre());
        arrayRpta.add(this.getSApellido());
        arrayRpta.add(Integer.toString(this.getNDni()));
        arrayRpta.add(this.getSEmail());
        arrayRpta.add(Integer.toString(this.getNColor()));
        
        return arrayRpta;
    }
    public static String[] getArrayHeaders() {
        String[] nombreColumnas = {"Departamento", "Nombres", "Apellidos",
            "DNI", "Emailc", "Estado"};
        return nombreColumnas;
    }
}
