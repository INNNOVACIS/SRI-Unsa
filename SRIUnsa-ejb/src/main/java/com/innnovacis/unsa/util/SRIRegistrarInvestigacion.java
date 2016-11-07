/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.util;

import com.innnovacis.unsa.model.SRIActividadInvestigacion;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author will
 */
public class SRIRegistrarInvestigacion {
    
    private SRIActividadInvestigacion actividadInvestigacion;
    private MultipartFormDataInput archivo;

    public SRIActividadInvestigacion getActividadInvestigacion() {
        return actividadInvestigacion;
    }

    public void setActividadInvestigacion(SRIActividadInvestigacion actividadInvestigacion) {
        this.actividadInvestigacion = actividadInvestigacion;
    }

    public MultipartFormDataInput getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFormDataInput archivo) {
        this.archivo = archivo;
    }
    
}
