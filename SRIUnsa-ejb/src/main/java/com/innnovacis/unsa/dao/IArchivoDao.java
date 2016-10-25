/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIArchivo;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IArchivoDao {
    SRIArchivo Insert(SRIArchivo entidad);
    SRIArchivo Update(SRIArchivo entidad);
    boolean Delete(SRIArchivo entidad);
    SRIArchivo GetById(int idEntidad);
    List<SRIArchivo> GetAll();
    
}
