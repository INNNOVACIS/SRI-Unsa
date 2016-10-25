/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRITipoAsesoria;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoAsesoriaBusiness {
    int Insertar(SRITipoAsesoria entidad);
    boolean Update(SRITipoAsesoria entidad);
    boolean Delete(SRITipoAsesoria entidad);
    SRITipoAsesoria Get(int idEntidad);
    List<SRITipoAsesoria> GetAll();

}
