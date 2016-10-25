/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIEstado;
import com.innnovacis.unsa.model.SRISemestre;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IEstadoBusiness {
    int Insertar(SRIEstado entidad);
    boolean Update(SRIEstado entidad);
    boolean Delete(SRIEstado entidad);
    SRIEstado Get(int idEntidad);
    List<SRIEstado> GetAll();

}
