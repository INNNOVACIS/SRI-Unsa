/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIPlantillaDocumentoActividad;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPlantillaDocumentoActividadDao {
    SRIPlantillaDocumentoActividad Insert(SRIPlantillaDocumentoActividad entidad);
    SRIPlantillaDocumentoActividad Update(SRIPlantillaDocumentoActividad entidad);
    boolean Delete(SRIPlantillaDocumentoActividad entidad);
    List<SRIPlantillaDocumentoActividad> GetPlantillaDocumentoActividadByIdActividad(int idActividadInvestigacion);
}
