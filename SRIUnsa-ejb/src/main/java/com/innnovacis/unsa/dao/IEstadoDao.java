/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIEstado;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IEstadoDao {
    SRIEstado Insert(SRIEstado entidad);
    SRIEstado Update(SRIEstado entidad);
    boolean Delete(SRIEstado entidad);
    SRIEstado GetById(int idEntidad);
    List<SRIEstado> GetAll();  
}
