package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.*;

@Path("/temperaturas")
public class TemperaturasResource {

    //acá se crea la lista para las temperaturas
    private List<Temperatura> valores = new ArrayList<>();

    //agrga las temperaturas a una lista
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura Nueva(Temperatura temp){
        valores.add(temp);
        return temp;
    }

    //devuelve la lista
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list(){
        return Collections.unmodifiableList(valores);

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
