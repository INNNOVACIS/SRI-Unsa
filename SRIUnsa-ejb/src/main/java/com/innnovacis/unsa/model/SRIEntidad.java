
package com.innnovacis.unsa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SRIEntidad implements Serializable {
    
    @Column(name = "usercreacion")
    private String SUserCreacion;
    @Column(name = "usermodificacion")
    private String SUserModificacion;
    @Column(name = "fechacreacion")
    private Date DFechaCreacion;
    @Column(name = "fechamodificacion")
    private Date DFechaModificacion;
    @Column(name = "estado")
    private String SEstado;
    
    
    public String getSUserCreacion() {
        return SUserCreacion;
    }

    public void setSUserCreacion(String SUserCreacion) {
        this.SUserCreacion = SUserCreacion;
    }

    public String getSUserModificacion() {
        return SUserModificacion;
    }

    public void setSUserModificacion(String SUserModificacion) {
        this.SUserModificacion = SUserModificacion;
    }

    public Date getDFechaCreacion() {
        return DFechaCreacion;
    }

    public void setDFechaCreacion(Date DFechaCreacion) {
        this.DFechaCreacion = DFechaCreacion;
    }

    public Date getDFechaModificacion() {
        return DFechaModificacion;
    }

    public void setDFechaModificacion(Date DFechaModificacion) {
        this.DFechaModificacion = DFechaModificacion;
    }

    public String getSEstado() {
        return SEstado;
    }

    public void setSEstado(String SEstado) {
        this.SEstado = SEstado;
    }

    

}
