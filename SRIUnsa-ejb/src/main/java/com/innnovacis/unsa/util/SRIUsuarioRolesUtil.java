/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

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
public class SRIUsuarioRolesUtil implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idrol" )
    private int NIdRol;
    
    @Column(name = "idusuariorol" )
    private int NIdUsuarioRol;
    
    @Column(name = "nombreRol")
    private String SNombreRol;

    public int getNIdUsuarioRol() {
        return NIdUsuarioRol;
    }

    public void setNIdUsuarioRol(int NIdUsuarioRol) {
        this.NIdUsuarioRol = NIdUsuarioRol;
    }

    public int getNIdRol() {
        return NIdRol;
    }

    public void setNIdRol(int NIdRol) {
        this.NIdRol = NIdRol;
    }

    public String getSNombreRol() {
        return SNombreRol;
    }

    public void setSNombreRol(String SNombreRol) {
        this.SNombreRol = SNombreRol;
    }
    
}
