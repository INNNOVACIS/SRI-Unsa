/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRITipoAsesoria;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoAsesoriaDao {
    SRITipoAsesoria Insert(SRITipoAsesoria entidad);
    SRITipoAsesoria Update(SRITipoAsesoria entidad);
    boolean Delete(SRITipoAsesoria entidad);
    SRITipoAsesoria GetById(int idEntidad);
    List<SRITipoAsesoria> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRITipoAsesoria> GetPagina(SRIPaginacionObject object);
    
}
