/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIUsuarioRol;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import com.innnovacis.unsa.util.SRIUsuarioRolUtil;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IUsuarioRolBusiness {
    int Insertar(SRIUsuarioRol entidad);
    boolean Update(SRIUsuarioRol entidad);
    boolean Delete(SRIUsuarioRol entidad);
    SRIUsuarioRol Get(int idEntidad);
    List<SRIUsuarioRol> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIUsuarioRolUtil> GetPagina(SRIPaginacionObject object);

}
