
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
@Table(name = "personacolaborador" )
@NamedQueries({
    @NamedQuery(name="SRIPersonaColaborador.GetAll",query="SELECT p FROM SRIPersonaColaborador p"),
    @NamedQuery(name="SRIPersonaColaborador.GetById",query="SELECT p FROM SRIPersonaColaborador p WHERE p.NIdPersonaColaborador  = :idEntidad")
})
public class SRIPersonaColaborador  extends SRIEntidad implements Serializable  {
    
    @Id
    @GeneratedValue
    @Column(name = "idpersonacolaborador" )
    private int NIdPersonaColaborador;

    @Column(name = "idpersona" )
    private int NIdPersona;
    
    @Column(name = "idactividadinvestigacion" )
    private int NIdActividadInvestigacion;

    public int getNIdPersonaColaborador() {
        return NIdPersonaColaborador;
    }

    public void setNIdPersonaColaborador(int NIdPersonaColaborador) {
        this.NIdPersonaColaborador = NIdPersonaColaborador;
    }

    public int getNIdPersona() {
        return NIdPersona;
    }

    public void setNIdPersona(int NIdPersona) {
        this.NIdPersona = NIdPersona;
    }

    public int getNIdActividadInvestigacion() {
        return NIdActividadInvestigacion;
    }

    public void setNIdActividadInvestigacion(int NIdActividadInvestigacion) {
        this.NIdActividadInvestigacion = NIdActividadInvestigacion;
    }
    
    
}

