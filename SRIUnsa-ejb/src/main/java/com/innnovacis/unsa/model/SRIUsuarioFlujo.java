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
@Table(name = "usuarioflujo" )
@NamedQueries({
    @NamedQuery(name="SRIUsuarioFlujo.GetAll",query="SELECT p FROM SRIUsuarioFlujo p"),
    @NamedQuery(name="SRIUsuarioFlujo.GetById",query="SELECT p FROM SRIUsuarioFlujo p WHERE p.NIdUsuarioFlujo = :idEntidad")
})
public class SRIUsuarioFlujo  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idusuarioflujo" )
    private int NIdUsuarioFlujo;
    

    @Column(name = "idarista")
    private int NIdArista;

    
    @Column(name = "idusuario")
    private int NIdUsuario;

    



    public int getNIdUsuarioFlujo() {
        return NIdUsuarioFlujo;
    }

    public void setNIdUsuarioFlujo(int NIdUsuarioFlujo) {
        this.NIdUsuarioFlujo = NIdUsuarioFlujo;
    }

    public int getNIdArista() {
        return NIdArista;
    }

    public void setNIdArista(int NIdArista) {
        this.NIdArista = NIdArista;
    }

    public int getNIdUsuario() {
        return NIdUsuario;
    }

    public void setNIdUsuario(int NIdUsuario) {
        this.NIdUsuario = NIdUsuario;
    }





    

    
    

}

