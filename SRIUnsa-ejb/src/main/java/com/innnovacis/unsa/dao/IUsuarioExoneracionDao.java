/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRIUsuarioExoneracion;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author innnovacis
 */
@ApplicationScoped
public interface IUsuarioExoneracionDao {
    SRIUsuarioExoneracion Insert(SRIUsuarioExoneracion entidad);
    SRIUsuarioExoneracion Update(SRIUsuarioExoneracion entidad);
    boolean Delete(SRIUsuarioExoneracion entidad);
    SRIUsuarioExoneracion GetById(int idEntidad);
    List<SRIUsuarioExoneracion> GetAll();
    SRIUsuarioExoneracion GetByIdUsuarioIdSemestre(int idUsuario, int idSemestre);
}
