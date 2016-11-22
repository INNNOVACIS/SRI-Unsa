/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIFlujoActor;
import com.innnovacis.unsa.model.SRISemestre;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IFlujoActorBusiness {
    int Insertar(SRIFlujoActor entidad);
    boolean Update(SRIFlujoActor entidad);
    boolean Delete(SRIFlujoActor entidad);
    SRIFlujoActor Get(int idEntidad);
    List<SRIFlujoActor> GetAll();
    int GetTotalPaginacion(SRIPaginacionObject object);
    List<SRIFlujoActor> GetPagina(SRIPaginacionObject object);
}
