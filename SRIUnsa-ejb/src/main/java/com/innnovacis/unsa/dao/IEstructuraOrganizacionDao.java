/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IEstructuraOrganizacionDao {
    SRIEstructuraOrganizacion Insert(SRIEstructuraOrganizacion entidad);
    SRIEstructuraOrganizacion Update(SRIEstructuraOrganizacion entidad);
    boolean Delete(SRIEstructuraOrganizacion entidad);
    SRIEstructuraOrganizacion GetById(int idEntidad);
    List<SRIEstructuraOrganizacion> GetAll();
    
}
