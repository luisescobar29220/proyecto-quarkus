package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/temperaturas")
public class TemperaturasResource {

    private TemperaturasService temperaturas;

    @Inject
    public TemperaturasResource(TemperaturasService temperaturas){
        this.temperaturas = temperaturas;
    }

    //agrga las temperaturas a una lista
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura Nueva(Temperatura temp){
        temperaturas.addTemperatura(temp);
        return temp;
    }

    //devuelve la lista
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list(){
        return temperaturas.obtenerTemperaturas();

    }

    @GET
    @Path("/maxima")
    @Produces(MediaType.TEXT_PLAIN)
    public Response maxima(){
        if (temperaturas.isEmpty()){
            return Response.status(404).entity("no hay temperaturas").build();
        } else{
            int temperaturaMaxima = temperaturas.maxima();
            return Response.ok(Integer.toString(temperaturaMaxima)).build();
        }
    }

    @GET
    @Path("{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura sacar(@PathParam("ciudad") String ciudad){
        return temperaturas.sacarTemperatura(ciudad).
                orElseThrow(()->
                new NoSuchElementException("no hay registro para la ciudad "+ ciudad));

    }
/*
    @GET
    @Path("/una")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura medicion(){
        return new Temperatura("lima",15,22);
    }
*/
}
