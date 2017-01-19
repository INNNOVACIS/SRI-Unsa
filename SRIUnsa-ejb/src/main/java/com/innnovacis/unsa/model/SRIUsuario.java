
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
@Table(name = "usuario" )
@NamedQueries({
    @NamedQuery(name="SRIUsuario.GetAll",query="SELECT p FROM SRIUsuario p"),
    @NamedQuery(name="SRIUsuario.GetById",query="SELECT p FROM SRIUsuario p WHERE p.NIdUsuario  = :idEntidad"),
    @NamedQuery(name="SRIUsuario.Autenticar",query="SELECT p FROM SRIUsuario p WHERE p.SUsuarioLogin = :usuario and p.SUsuarioPassword = :password")
})
public class SRIUsuario  extends SRIEntidad implements Serializable  {
    
    @Id
    @GeneratedValue
    @Column(name = "idusuario" )
    private int NIdUsuario;
    
    @Column(name = "idpersona" )
    private int NIdPersona;
    
    @Column(name = "idestructuraorganizacion" )
    private int NIdEstructuraOrganizacion;
    
    @Column(name = "iddepartamento" )
    private int NIdDepartamento;
    
    @Column(name = "usuariologin")
    private String SUsuarioLogin;

    @Column(name = "usuariopassword")
    private String SUsuarioPassword;

    @Column(name = "usuarioemail")
    private String SUsuarioEmail;
    
    @Column(name = "codigo")
    private String SCodigo;
    
    @Column(name = "condicion")
    private String SCondicion;

    public int getNIdDepartamento() {
        return NIdDepartamento;
    }

    public void setNIdDepartamento(int NIdDepartamento) {
        this.NIdDepartamento = NIdDepartamento;
    }

    public int getNIdPersona() {
        return NIdPersona;
    }

    public void setNIdPersona(int NIdPersona) {
        this.NIdPersona = NIdPersona;
    }

    public int getNIdEstructuraOrganizacion() {
        return NIdEstructuraOrganizacion;
    }

    public void setNIdEstructuraOrganizacion(int NIdEstructuraOrganizacion) {
        this.NIdEstructuraOrganizacion = NIdEstructuraOrganizacion;
    }

    public String getSUsuarioEmail() {
        return SUsuarioEmail;
    }

    public void setSUsuarioEmail(String SUsuarioEmail) {
        this.SUsuarioEmail = SUsuarioEmail;
    }

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

    public String getSCodigo() {
        return SCodigo;
    }

    public void setSCodigo(String SCodigo) {
        this.SCodigo = SCodigo;
    }

    public String getSCondicion() {
        return SCondicion;
    }

    public void setSCondicion(String SCondicion) {
        this.SCondicion = SCondicion;
    }
    
}

