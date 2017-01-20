/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import java.io.Serializable;
import java.util.ArrayList;
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
public class SRITotalTipoActividad implements Serializable{
    
    @Id
    @Column(name = "idtipoactividadinvestigacion")
    private int NIdTipoActividadInvestigacion;
    
    @Column(name = "nombreactividadinvestigacion")
    private String SNombreActividadInvestigacion;
    
    @Column(name = "total")
    private int NTotal;

    public int getNIdTipoActividadInvestigacion() {
        return NIdTipoActividadInvestigacion;
    }

    public void setNIdTipoActividadInvestigacion(int NIdTipoActividadInvestigacion) {
        this.NIdTipoActividadInvestigacion = NIdTipoActividadInvestigacion;
    }

    public String getSNombreActividadInvestigacion() {
        return SNombreActividadInvestigacion;
    }

    public void setSNombreActividadInvestigacion(String SNombreActividadInvestigacion) {
        this.SNombreActividadInvestigacion = SNombreActividadInvestigacion;
    }

    public int getNTotal() {
        return NTotal;
    }

    public void setNTotal(int NTotal) {
        this.NTotal = NTotal;
    }
    
    public ArrayList<String> getArrayDatos() {
        ArrayList<String> arrayRpta = new ArrayList<String>();
        arrayRpta.add(this.getSNombreActividadInvestigacion());
        arrayRpta.add(String.valueOf(this.getNTotal()));
        
        return arrayRpta;
    }
    public static String[] getArrayHeaders() {
        String[] nombreColumnas = {"Activos", "Inactivos"};
        return nombreColumnas;
    }
}
