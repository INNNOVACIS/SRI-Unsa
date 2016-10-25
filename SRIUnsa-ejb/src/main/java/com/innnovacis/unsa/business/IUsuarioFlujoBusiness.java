/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRIUsuarioFlujo;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IUsuarioFlujoBusiness {
    int Insertar(SRIUsuarioFlujo entidad);
    boolean Update(SRIUsuarioFlujo entidad);
    boolean Delete(SRIUsuarioFlujo entidad);
    SRIUsuarioFlujo Get(int idEntidad);
    List<SRIUsuarioFlujo> GetAll();

}
