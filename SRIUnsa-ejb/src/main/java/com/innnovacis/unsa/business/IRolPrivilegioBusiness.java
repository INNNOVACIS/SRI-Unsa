/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIRolPrivilegio;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IRolPrivilegioBusiness {
    int Insertar(SRIRolPrivilegio entidad);
    boolean Update(SRIRolPrivilegio entidad);
    boolean Delete(SRIRolPrivilegio entidad);
    SRIRolPrivilegio Get(int idEntidad);
    List<SRIRolPrivilegio> GetAll();

}
