/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIPersonaColaborador;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IPersonaColaboradorDao {
    
    SRIPersonaColaborador Insert(SRIPersonaColaborador entidad);
    SRIPersonaColaborador Update(SRIPersonaColaborador entidad);
    boolean Delete(SRIPersonaColaborador entidad);
}
