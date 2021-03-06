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
@Table(name = "privilegio" )
@NamedQueries({
    @NamedQuery(name="SRIPrivilegio.GetAll",query="SELECT p FROM SRIPrivilegio p"),
    @NamedQuery(name="SRIPrivilegio.GetById",query="SELECT p FROM SRIPrivilegio p WHERE p.NIdPrivilegio = :idEntidad")
})
public class SRIPrivilegio  extends SRIEntidad implements Serializable  {
    
    @Id
    @GeneratedValue
    @Column(name = "idprivilegio" )
    private int NIdPrivilegio;

    @Column(name = "nombreprivilegio")
    private String SNombrePrivilegio;

    @Column(name = "urlprivilegio")
    private String SUrlPrivilegio;
    
    @Column(name = "idpadre")
    private int NIdPadre;

    public int getNIdPadre() {
        return NIdPadre;
    }

    public void setNIdPadre(int NIdPadre) {
        this.NIdPadre = NIdPadre;
    }

    public String getSNombrePrivilegio() {
        return SNombrePrivilegio;
    }

    public void setSNombrePrivilegio(String SNombrePrivilegio) {
        this.SNombrePrivilegio = SNombrePrivilegio;
    }

    public String getSUrlPrivilegio() {
        return SUrlPrivilegio;
    }

    public void setSUrlPrivilegio(String SUrlPrivilegio) {
        this.SUrlPrivilegio = SUrlPrivilegio;
    }
    
    public int getNIdPrivilegio() {
        return NIdPrivilegio;
    }

    public void setNIdPrivilegio(int NIdPrivilegio) {
        this.NIdPrivilegio = NIdPrivilegio;
    }

    public String getsNombrePrivilegio() {
        return SNombrePrivilegio;
    }

    public void setsNombrePrivilegio(String sNombrePrivilegio) {
        this.SNombrePrivilegio = sNombrePrivilegio;
    }

}

