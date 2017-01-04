/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.business;

import com.innnovacis.unsa.model.SRITipoInvestigador;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Gen-Tya-TIConsul1
 */
@ApplicationScoped
public interface ITipoInvestigadorBusiness {
    int Insertar(SRITipoInvestigador entidad);
    boolean Update(SRITipoInvestigador entidad);
    boolean Delete(SRITipoInvestigador entidad);
    SRITipoInvestigador Get(int idEntidad);
    List<SRITipoInvestigador> GetAll();
    
    int GetTotalPaginacion(SRIPaginacionObject entidad);
    List<SRITipoInvestigador>  GetPagina(SRIPaginacionObject entidad);
}
