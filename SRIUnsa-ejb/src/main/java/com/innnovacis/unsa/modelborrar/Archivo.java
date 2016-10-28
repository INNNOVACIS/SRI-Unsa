/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.modelborrar;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author innnovacis
 */
@Entity
@Table(name = "archivonuevo")
public class Archivo implements Serializable {
   
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "file")
    private Blob file;
    
    @Column(name = "estado")
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }
}