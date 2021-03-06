/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIArchivo;
import com.innnovacis.unsa.util.SRIArchivoUtil;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IArchivoDao {
    SRIArchivo Insert(SRIArchivo entidad);
    SRIArchivo Update(SRIArchivo entidad);
    boolean Delete(int idEntidad);
    SRIArchivo GetById(int idEntidad);
    List<SRIArchivoUtil> GetAll();
    List<SRIArchivoUtil> GetArchivosById(int id);
    Response descargarArchivo(int id);
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIArchivoUtil> GetPagina(SRIPaginacionObject object);
}
