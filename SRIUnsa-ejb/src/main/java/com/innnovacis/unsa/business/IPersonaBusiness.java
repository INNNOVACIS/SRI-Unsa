/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIPersona;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IPersonaBusiness {
    
    int Insertar(SRIPersona entidad);
    boolean Update(SRIPersona entidad);
    boolean Delete(SRIPersona entidad);
    SRIPersona Get(int idEntidad);
    List<SRIPersona> GetAll();
    
    boolean updatePersona(SRIPersona persona);
}
