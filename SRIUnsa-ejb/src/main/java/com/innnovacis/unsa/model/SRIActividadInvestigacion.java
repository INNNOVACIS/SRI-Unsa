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
@Table(name = "actividadinvestigacion" )
@NamedQueries({
    @NamedQuery(name="SRIActividadInvestigacion.GetAll",query="SELECT p FROM SRIActividadInvestigacion p"),
    @NamedQuery(name="SRIActividadInvestigacion.GetById",query="SELECT p FROM SRIActividadInvestigacion p WHERE p.NIdActividadInvestigacion = :idEntidad")
})
public class SRIActividadInvestigacion  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idactividadinvestigacion" )
    private int NIdActividadInvestigacion;

    @Column(name = "idtipoactividadinvestigacion")
    private int NIdTipoActividadInvestigacion;

    @Column(name = "horas")
    private int NHoras;

    @Column(name = "tipoproduccion")
    private String SRITipoProduccion;
    
    @Column(name = "fondoconcursable")
    private String SFondoConcursable;
    
    @Column(name = "tipoasesoria")
    private String STipoAsesoria;
    
    @Column(name = "semestre")
    private String SSemestre;
    
    @Column(name = "facultad")
    private String SFacultad;
    
    @Column(name = "escuela")
    private String SEscuela;
    
    @Column(name = "departamento")
    private String SDepartamento;
    
    @Column(name = "areainvestigacion")
    private String SAreaInvestigacion;
    
    @Column(name = "subareainvestigacion")
    private String SSubAreaInvestigacion;
    
    @Column(name = "disciplina")
    private String SDisciplina;
     
    @Column(name = "tipolabor")
    private String STipoLabor;
    
    @Column(name = "nombreactividadinvestigacion")
    private String SNombreActividadInvestigacion;
    
    @Column(name = "descripcionactividad")
    private String SDescripcionActividad;
    
    
    public int getNIdActividadInvestigacion() {
        return NIdActividadInvestigacion;
    }

    public void setNIdActividadInvestigacion(int NIdActividadInvestigacion) {
        this.NIdActividadInvestigacion = NIdActividadInvestigacion;
    }

    public int getNIdTipoActividadInvestigacion() {
        return NIdTipoActividadInvestigacion;
    }

    public void setNIdTipoActividadInvestigacion(int NIdTipoActividadInvestigacion) {
        this.NIdTipoActividadInvestigacion = NIdTipoActividadInvestigacion;
    }

    public int getNHoras() {
        return NHoras;
    }

    public void setNHoras(int NHoras) {
        this.NHoras = NHoras;
    }

    public String getSRITipoProduccion() {
        return SRITipoProduccion;
    }

    public void setSRITipoProduccion(String SRITipoProduccion) {
        this.SRITipoProduccion = SRITipoProduccion;
    }

    public String getSFondoConcursable() {
        return SFondoConcursable;
    }

    public void setSFondoConcursable(String SFondoConcursable) {
        this.SFondoConcursable = SFondoConcursable;
    }

    public String getSTipoAsesoria() {
        return STipoAsesoria;
    }

    public void setSTipoAsesoria(String STipoAsesoria) {
        this.STipoAsesoria = STipoAsesoria;
    }

    public String getSSemestre() {
        return SSemestre;
    }

    public void setSSemestre(String SSemestre) {
        this.SSemestre = SSemestre;
    }

    public String getSFacultad() {
        return SFacultad;
    }

    public void setSFacultad(String SFacultad) {
        this.SFacultad = SFacultad;
    }

    public String getSEscuela() {
        return SEscuela;
    }

    public void setSEscuela(String SEscuela) {
        this.SEscuela = SEscuela;
    }

    public String getSDepartamento() {
        return SDepartamento;
    }

    public void setSDepartamento(String SDepartamento) {
        this.SDepartamento = SDepartamento;
    }

    public String getSAreaInvestigacion() {
        return SAreaInvestigacion;
    }

    public void setSAreaInvestigacion(String SAreaInvestigacion) {
        this.SAreaInvestigacion = SAreaInvestigacion;
    }

    public String getSSubAreaInvestigacion() {
        return SSubAreaInvestigacion;
    }

    public void setSSubAreaInvestigacion(String SSubAreaInvestigacion) {
        this.SSubAreaInvestigacion = SSubAreaInvestigacion;
    }

    public String getSDisciplina() {
        return SDisciplina;
    }

    public void setSDisciplina(String SDisciplina) {
        this.SDisciplina = SDisciplina;
    }

    public String getSTipoLabor() {
        return STipoLabor;
    }

    public void setSTipoLabor(String STipoLabor) {
        this.STipoLabor = STipoLabor;
    }

    public String getSNombreActividadInvestigacion() {
        return SNombreActividadInvestigacion;
    }

    public void setSNombreActividadInvestigacion(String SNombreActividadInvestigacion) {
        this.SNombreActividadInvestigacion = SNombreActividadInvestigacion;
    }

    public String getSDescripcionActividad() {
        return SDescripcionActividad;
    }

    public void setSDescripcionActividad(String SDescripcionActividad) {
        this.SDescripcionActividad = SDescripcionActividad;
    }
    
    

}

