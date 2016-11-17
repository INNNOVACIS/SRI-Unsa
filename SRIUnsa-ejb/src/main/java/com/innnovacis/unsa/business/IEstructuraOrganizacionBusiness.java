/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IEstructuraOrganizacionBusiness {
    int Insertar(SRIEstructuraOrganizacion entidad);
    boolean Update(SRIEstructuraOrganizacion entidad);
    boolean Delete(SRIEstructuraOrganizacion entidad);
    SRIEstructuraOrganizacion Get(int idEntidad);
    List<SRIEstructuraOrganizacion> GetAll();
    int GetTotalPaginacion (SRIPaginacionObject object);
    List<SRIEstructuraOrganizacion> GetPagina (SRIPaginacionObject object);

}
