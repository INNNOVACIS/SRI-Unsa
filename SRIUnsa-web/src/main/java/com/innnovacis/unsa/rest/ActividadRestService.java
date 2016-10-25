/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innnovacis.unsa.rest;

import java.util.HashMap;
import java.util.Iterator;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author innnovacis
 */
@Path("/actividades")
@RequestScoped
public class ActividadRestService {

    @POST
    @Path("/filtrarRevisadas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String Filtro(String parametros) {
        
        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(parametros);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key); 
            map.put(key, value);

        }

        System.out.println("json : "+jObject);
        System.out.println("map : "+map.get("escuela"));
        System.out.println("valores ======> " + parametros);
        return parametros;
    }
}
