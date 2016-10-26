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
@Table(name = "estructuraorganizacion" )
@NamedQueries({
    @NamedQuery(name="SRIEstructuraOrganizacion.GetAll",query="SELECT p FROM SRIEstructuraOrganizacion p"),
    @NamedQuery(name="SRIEstructuraOrganizacion.GetById",query="SELECT p FROM SRIEstructuraOrganizacion p WHERE p.NIdEstructuraOrganizacion = :idEntidad")
})
public class SRIEstructuraOrganizacion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idestructuraorganizacion" )
    private int NIdEstructuraOrganizacion;

    @Column(name = "idtiponivel")
    private int NIdTipoNivel;
    
    @Column(name = "nombreestructuraorganizacion")
    private String SNombreEstructuraOrganizacion;
    
    @Column(name = "idpadre")
    private int NIdPadre;
    
    @Column(name = "nivel")
    private String SNivel;
    
    public int getNIdEstructuraOrganizacion() {
        return NIdEstructuraOrganizacion;
    }

    public void setNIdEstructuraOrganizacion(int NIdEstructuraOrganizacion) {
        this.NIdEstructuraOrganizacion = NIdEstructuraOrganizacion;
    }

    public int getNIdTipoNivel() {
        return NIdTipoNivel;
    }

    public void setNIdTipoNivel(int NIdTipoNivel) {
        this.NIdTipoNivel = NIdTipoNivel;
    }

    public String getSNombreEstructuraOrganizacion() {
        return SNombreEstructuraOrganizacion;
    }

    public void setSNombreEstructuraOrganizacion(String SNombreEstructuraOrganizacion) {
        this.SNombreEstructuraOrganizacion = SNombreEstructuraOrganizacion;
    }

    public int getNIdPadre() {
        return NIdPadre;
    }

    public void setNIdPadre(int NIdPadre) {
        this.NIdPadre = NIdPadre;
    }

    public String getSNivel() {
        return SNivel;
    }

    public void setSNivel(String SNivel) {
        this.SNivel = SNivel;
    }

   


    
    

}

