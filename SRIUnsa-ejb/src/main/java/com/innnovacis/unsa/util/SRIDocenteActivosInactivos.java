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
public class SRIDocenteActivosInactivos implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "activos" )
    private int NActivos;
    
    @Column(name = "inactivos" )
    private int NInactivos;

    public int getNActivos() {
        return NActivos;
    }

    public void setNActivos(int NActivos) {
        this.NActivos = NActivos;
    }

    public int getNInactivos() {
        return NInactivos;
    }

    public void setNInactivos(int NInactivos) {
        this.NInactivos = NInactivos;
    }
   
}
