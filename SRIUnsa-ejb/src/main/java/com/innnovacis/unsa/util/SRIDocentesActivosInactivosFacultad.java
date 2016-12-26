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
public class SRIDocentesActivosInactivosFacultad implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "idestructuraorganizacion" )
    private int NIdEstructuraOrganizacion;
    
    @Column(name = "nombreestructuraorganizacion" )
    private String SNombreEstructuraOrganizacion;
    
    @Column(name = "total" )
    private int NTotal;
    
    @Column(name = "activos" )
    private int NActivos;
    
    @Column(name = "inactivos" )
    private int NInactivos;

    public int getNIdEstructuraOrganizacion() {
        return NIdEstructuraOrganizacion;
    }

    public void setNIdEstructuraOrganizacion(int NIdEstructuraOrganizacion) {
        this.NIdEstructuraOrganizacion = NIdEstructuraOrganizacion;
    }

    public String getSNombreEstructuraOrganizacion() {
        return SNombreEstructuraOrganizacion;
    }

    public void setSNombreEstructuraOrganizacion(String SNombreEstructuraOrganizacion) {
        this.SNombreEstructuraOrganizacion = SNombreEstructuraOrganizacion;
    }

    public int getNTotal() {
        return NTotal;
    }

    public void setNTotal(int NTotal) {
        this.NTotal = NTotal;
    }

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
