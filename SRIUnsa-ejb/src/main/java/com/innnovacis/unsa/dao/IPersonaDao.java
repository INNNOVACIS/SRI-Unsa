/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIPersona;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IPersonaDao {
    
    SRIPersona Insert(SRIPersona entidad);
    SRIPersona Update(SRIPersona entidad);
    boolean Delete(SRIPersona entidad);
    SRIPersona GetById(int idEntidad);
    List<SRIPersona> GetAll();
}
