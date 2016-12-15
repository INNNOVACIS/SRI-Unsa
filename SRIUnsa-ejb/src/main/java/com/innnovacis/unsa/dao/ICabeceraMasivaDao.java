/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRICabeceraMasiva;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ICabeceraMasivaDao {
    
    SRICabeceraMasiva Insert(SRICabeceraMasiva entidad);
    SRICabeceraMasiva Update(SRICabeceraMasiva entidad);
    SRICabeceraMasiva GetById(int idEntidad);
    boolean Delete(SRICabeceraMasiva entidad);
}
