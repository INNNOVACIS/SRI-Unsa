/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.model.SRIPlantillaDocumento;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPlantillaDocumentoBusiness {
    int Insertar(SRIPlantillaDocumento entidad);
    boolean Update(SRIPlantillaDocumento entidad);
    boolean Delete(SRIPlantillaDocumento entidad);
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRIPlantillaDocumento> GetPagina (SRIPaginacionObject entidad);
    List<SRIPlantillaDocumento> GetPlantillaDocumentoByFacultad(SRIEstructuraOrganizacion entidad);
}
