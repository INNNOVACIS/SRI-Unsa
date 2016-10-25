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
@Table(name = "rolprivilegio" )
@NamedQueries({
    @NamedQuery(name="SRIRolPrivilegio.GetAll",query="SELECT p FROM SRIRolPrivilegio p"),
    @NamedQuery(name="SRIRolPrivilegio.GetById",query="SELECT p FROM SRIRolPrivilegio p WHERE p.NIdRolPrivilegio = :idEntidad")
})
public class SRIRolPrivilegio  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idrolprivilegio" )
    private int NIdRolPrivilegio;


    @Column(name = "idrol")
    private int NIdRol;

    
    @Column(name = "idprivilegio")
    private int NIdPrivilegio;
  
    
    
    public int getNIdRolPrivilegio() {
        return NIdRolPrivilegio;
    }

    public void setNIdRolPrivilegio(int NIdRolPrivilegio) {
        this.NIdRolPrivilegio = NIdRolPrivilegio;
    }

    public int getNIdRol() {
        return NIdRol;
    }

    public void setNIdRol(int NIdRol) {
        this.NIdRol = NIdRol;
    }

    public int getNIdPrivilegio() {
        return NIdPrivilegio;
    }

    public void setNIdPrivilegio(int NIdPrivilegio) {
        this.NIdPrivilegio = NIdPrivilegio;
    }




    

    
    

}

