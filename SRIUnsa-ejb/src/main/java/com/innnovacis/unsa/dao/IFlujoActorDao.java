/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IFlujoActorDao {
    SRIFlujoActor Insert(SRIFlujoActor entidad);
    SRIFlujoActor Update(SRIFlujoActor entidad);
    boolean Delete(SRIFlujoActor entidad);
    SRIFlujoActor GetById(int idEntidad);
    List<SRIFlujoActor> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIFlujoActor> GetPagina(SRIPaginacionObject object);
}
