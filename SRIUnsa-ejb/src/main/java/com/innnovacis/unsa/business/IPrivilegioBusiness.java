/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIPrivilegio;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPrivilegioBusiness {
    int Insertar(SRIPrivilegio entidad);
    boolean Update(SRIPrivilegio entidad);
    boolean Delete(SRIPrivilegio entidad);
    SRIPrivilegio Get(int idEntidad);
    List<SRIPrivilegio> GetAll();

}
