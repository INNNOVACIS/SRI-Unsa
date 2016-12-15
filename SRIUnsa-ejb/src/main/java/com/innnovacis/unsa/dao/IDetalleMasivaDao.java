/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;


import com.innnovacis.unsa.model.SRIDetalleMasiva;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface IDetalleMasivaDao {
    
    SRIDetalleMasiva Insert(SRIDetalleMasiva entidad);
    SRIDetalleMasiva Update(SRIDetalleMasiva entidad);
    SRIDetalleMasiva GetById(int idEntidad);
    boolean Delete(SRIDetalleMasiva entidad);
}
