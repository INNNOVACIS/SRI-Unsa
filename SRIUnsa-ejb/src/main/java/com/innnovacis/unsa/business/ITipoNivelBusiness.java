/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRITipoNivel;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoNivelBusiness {
    int Insertar(SRITipoNivel entidad);
    boolean Update(SRITipoNivel entidad);
    boolean Delete(SRITipoNivel entidad);
    SRITipoNivel Get(int idEntidad);
    List<SRITipoNivel> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRITipoNivel> GetPagina(SRIPaginacionObject object);

}
