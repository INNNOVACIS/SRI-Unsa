
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
@Table(name = "persona" )
@NamedQueries({
    @NamedQuery(name="SRIPersona.GetAll",query="SELECT p FROM SRIPersona p"),
    @NamedQuery(name="SRIPersona.GetById",query="SELECT p FROM SRIPersona p WHERE p.NIdPersona  = :idEntidad")
})
public class SRIPersona  extends SRIEntidad implements Serializable  {
    
    @Id
    @GeneratedValue
    @Column(name = "idpersona" )
    private int NIdPersona;

    @Column(name = "email")
    private String SEmail;
    
    @Column(name = "dni")
    private int NDni;
    
    @Column(name = "nombre")
    private String SNombre;
    
    @Column(name = "apellido")
    private String SApellido;

    public int getNIdPersona() {
        return NIdPersona;
    }

    public void setNIdPersona(int NIdPersona) {
        this.NIdPersona = NIdPersona;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
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

}

