
package com.innnovacis.unsa.rest;

import com.innnovacis.unsa.borrar.IPersonaBusiness;

import com.innnovacis.unsa.modelborrar.Persona;
import com.innnovacis.unsa.service.RegistrarPersona;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/helloWorld")
@RequestScoped
public class HelloWorldService {
    
    @Inject
    private IPersonaBusiness personaBusiness;
    
    @Inject
    private RegistrarPersona registrarPersona;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> helloWorld() {
        return personaBusiness.findAll();
        /*CREA POST TAMBIEN Y QUE RECIBA PARAMETROS yo borre ese registrar investigacion porque sale ese error*/
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response createPersona(Persona persona) {

        Response.ResponseBuilder builder = null;

        try {
            // Validates member using bean validation
            //validateMember(persona);

            registrarPersona.registrar(persona);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            //builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("email", "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeletePersonById(@PathParam("id") int id) {
//        
//        int v = personaRepository.deleteById(id);
//        //System.out.print("este es el idddddd"+id);
//        if (v == 1) {
//            String output = "DELETE: " + v;
//            return Response.status(200).entity(output).build();
//        }else {throw new ValidationException("borrado no completado"+v);}
////        return person;

return null;
    }
    
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String UpdateData(Persona persona) {
        
        //int v = personaRepository.UpdateD(persona);
        //System.out.print("este es el idddddd"+id);
        
//        if (v == 1) {
//            String output = "Update: " + persona.getNombre();
//            return Response.status(200).entity(output).build();
//        }else {throw new ValidationException("error actualizar"+ persona.getNombre());
//            
//        }
            String hola = "hola";
        return hola;
    }

}
