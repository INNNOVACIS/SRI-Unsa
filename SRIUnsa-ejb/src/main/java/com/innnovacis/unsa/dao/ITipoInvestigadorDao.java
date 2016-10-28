/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.dao;

import com.innnovacis.unsa.model.SRITipoInvestigador;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoInvestigadorDao {
    SRITipoInvestigador Insert(SRITipoInvestigador entidad);
    SRITipoInvestigador Update(SRITipoInvestigador entidad);
    boolean Delete(SRITipoInvestigador entidad);
    SRITipoInvestigador GetById(int idEntidad);
    List<SRITipoInvestigador> GetAll();
    
}
