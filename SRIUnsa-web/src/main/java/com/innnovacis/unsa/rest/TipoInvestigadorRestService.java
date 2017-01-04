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
import com.innnovacis.unsa.model.SRITipoActividadInvestigacion;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author innnovacis
 */
@Path("/tipoInvestigador")
@RequestScoped
public class TipoInvestigadorRestService {

    @Inject
    private ITipoInvestigadorBusiness tipoInvestigadorBusiness;
    
    @POST
    @Path("/getTipoInvestigadorByPagina")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response paginacionAreaInvestigacion(SRIPaginacionObject entidad) {
        int total = tipoInvestigadorBusiness.GetTotalPaginacion(entidad);
        List<SRITipoInvestigador> lista = tipoInvestigadorBusiness.GetPagina(entidad);

        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("total", total);
        responseObj.put("lista", lista);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
        
        return builder.build();
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public SRITipoActividadInvestigacion GetById(@PathParam("id") long id) {
        
        return null;
    }
    
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
