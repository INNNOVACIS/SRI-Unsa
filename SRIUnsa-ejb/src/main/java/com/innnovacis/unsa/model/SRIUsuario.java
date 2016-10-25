
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
@Table(name = "Usuario" )
@NamedQueries({
    @NamedQuery(name="SRIUsuario.GetAll",query="SELECT p FROM SRIUsuario p"),
    @NamedQuery(name="SRIUsuario.GetById",query="SELECT p FROM SRIUsuario p WHERE p.NIdUsuario  = :idEntidad")
})
public class SRIUsuario  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idusuario" )
    private int NIdUsuario;
    
    @Column(name = "usuariologin")
    private String SUsuarioLogin;

    @Column(name = "usuariopassword")
    private String SUsuarioPassword;



    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }

    public String getSUsuarioLogin() {
        return SUsuarioLogin;
    }

    public void setSUsuarioLogin(String SUsuarioLogin) {
        this.SUsuarioLogin = SUsuarioLogin;
    }

    public String getSUsuarioPassword() {
        return SUsuarioPassword;
    }

    public void setSUsuarioPassword(String SUsuarioPassword) {
        this.SUsuarioPassword = SUsuarioPassword;
    }
    


  



    

    
    

}

