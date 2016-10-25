/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIPrivilegio;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IPrivilegioDao {
    SRIPrivilegio Insert(SRIPrivilegio entidad);
    SRIPrivilegio Update(SRIPrivilegio entidad);
    boolean Delete(SRIPrivilegio entidad);
    SRIPrivilegio GetById(int idEntidad);
    List<SRIPrivilegio> GetAll();
    
}
