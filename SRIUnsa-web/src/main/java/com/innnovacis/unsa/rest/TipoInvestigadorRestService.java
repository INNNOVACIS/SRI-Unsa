/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.model.SRITipoInvestigador;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.innnovacis.unsa.business.ITipoInvestigadorBusiness;

/**
 *
 * @author innnovacis
 */
@Path("/tipoInvestigador")
@RequestScoped
public class TipoInvestigadorRestService {

    @Inject
    private ITipoInvestigadorBusiness tipoInvestigadorBusiness;
    
    @GET
    @Path("/listarTipoInvestigador")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SRITipoInvestigador> GetAll(){
        return tipoInvestigadorBusiness.GetAll();
    }
    
    @POST
    @Path("/registrarTipoInvestigador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int SaveTipoInvestigador(SRITipoInvestigador tipoInvestigador){
        return tipoInvestigadorBusiness.Insertar(tipoInvestigador);
    }
    
    @PUT
    @Path("/updateTipoInvestigador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean UpdateTipoInvestigador(SRITipoInvestigador tipoInvestigador){
        return tipoInvestigadorBusiness.Update(tipoInvestigador);
    }
    
    @PUT
    @Path("/deleteTipoInvestigador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean DeleteTipoInvestigador(SRITipoInvestigador tipoInvestigador){
        return tipoInvestigadorBusiness.Delete(tipoInvestigador);
    }
}
