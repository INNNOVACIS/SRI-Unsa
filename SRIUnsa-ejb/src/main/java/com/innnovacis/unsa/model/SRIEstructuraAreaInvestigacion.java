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
@Table(name = "estructuraareainvestigacion" )
@NamedQueries({
    @NamedQuery(name="SRIEstructuraAreaInvestigacion.GetAll",query="SELECT p FROM SRIEstructuraAreaInvestigacion p"),
    @NamedQuery(name="SRIEstructuraAreaInvestigacion.GetById",query="SELECT p FROM SRIEstructuraAreaInvestigacion p WHERE p.NIdEstructura = :idEntidad")
})
public class SRIEstructuraAreaInvestigacion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idestructura" )
    private int NIdEstructura;

    @Column(name = "nombre")
    private String sNombre;
    
    @Column(name = "idpadre")
    private int nIdPadre;
    
    @Column(name = "nivel")
    private String sNivel;
    
    
    public int getNIdEstructura() {
        return NIdEstructura;
    }

    public void setNIdEstructura(int NIdEstructura) {
        this.NIdEstructura = NIdEstructura;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public int getnIdPadre() {
        return nIdPadre;
    }

    public void setnIdPadre(int nIdPadre) {
        this.nIdPadre = nIdPadre;
    }

    public String getsNivel() {
        return sNivel;
    }

    public void setsNivel(String sNivel) {
        this.sNivel = sNivel;
    }




    
    

}

