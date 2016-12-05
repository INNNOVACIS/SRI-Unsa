
package com.innnovacis.unsa.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
