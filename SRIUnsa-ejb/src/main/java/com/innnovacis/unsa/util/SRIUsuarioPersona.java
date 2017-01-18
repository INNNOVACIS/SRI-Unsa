/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIEntidad;
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
public class SRIUsuarioPersona extends SRIEntidad implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idusuario" )
    private int NIdUsuario;
    
    @Column(name = "idpersona" )
    private int NIdPersona;
    
    @Column(name = "idestructuraorganizacion" )
    private int NIdEstructuraOrganizacion;
    
    @Column(name = "iddepartamento" )
    private int NidDepartamento;
    
    @Column(name = "usuariologin")
    private String SUsuarioLogin;

    @Column(name = "usuariopassword")
    private String SUsuarioPassword;

    @Column(name = "usuarioemail")
    private String SUsuarioEmail;
    
    @Column(name = "dni")
    private String NDni;
    
    @Column(name = "nombre")
    private String SNombre;
    
    @Column(name = "apellido")
    private String SApellido;

    public int getNidDepartamento() {
        return NidDepartamento;
    }

    public void setNidDepartamento(int NidDepartamento) {
        this.NidDepartamento = NidDepartamento;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
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

    public String getSUsuarioEmail() {
        return SUsuarioEmail;
    }

    public void setSUsuarioEmail(String SUsuarioEmail) {
        this.SUsuarioEmail = SUsuarioEmail;
    }

    public String getNDni() {
        return NDni;
    }

    public void setNDni(String NDni) {
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
