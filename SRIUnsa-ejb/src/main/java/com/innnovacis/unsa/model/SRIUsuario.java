
package com.innnovacis.unsa.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    @NamedQuery(name="SRIUsuario.GetById",query="SELECT p FROM SRIUsuario p WHERE p.NIdUsuario  = :idEntidad"),
    @NamedQuery(name="SRIUsuario.Autenticar",query="SELECT p FROM SRIUsuario p WHERE p.SUsuarioLogin = :usuario and p.SUsuarioPassword = :password")
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

    @Column(name = "usuarioemail")
    private String SUsuarioEmail;

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
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(getClass().getSimpleName());
        result.append( " {" );
        result.append(newLine);

        List<Field> fields = getAllModelFields(getClass());

        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                field.setAccessible(true);
                result.append(field.get(this));

            } catch ( IllegalAccessException ex ) {
    //                System.err.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");
        result.append(newLine);

        return result.toString();
    }

    private List<Field> getAllModelFields(Class aClass) {
        List<Field> fields = new ArrayList<>();
        do {
            Collections.addAll(fields, aClass.getDeclaredFields());
            aClass = aClass.getSuperclass();
        } while (aClass != null);
        return fields;
    }
  
}

