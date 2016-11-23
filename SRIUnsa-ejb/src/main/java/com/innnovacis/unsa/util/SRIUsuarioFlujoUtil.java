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
public class SRIUsuarioFlujoUtil implements Serializable{
    
    @Id
    @Column(name = "idUsuario")
    private int NIdUsuario;
    
    @Column(name = "usuariologin" )
    private String SUsuarioLogin;
    
    @Column(name = "actores" )
    private int NActores;

    public String getSUsuarioLogin() {
        return SUsuarioLogin;
    }

    public void setSUsuarioLogin(String SUsuarioLogin) {
        this.SUsuarioLogin = SUsuarioLogin;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }

    public int getNActores() {
        return NActores;
    }

    public void setNActores(int NActores) {
        this.NActores = NActores;
    }
    
}
