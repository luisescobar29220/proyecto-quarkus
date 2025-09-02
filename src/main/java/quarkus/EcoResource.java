package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.Optional;

@Path("saludar")
public class EcoResource {

    @GET
    public String saludar(@QueryParam("mensaje") String mensaje){
        return Optional.ofNullable(mensaje).orElse("no se que decir aqui");

        //es lo mismo que arriba
        /*if (mensaje == null){
            return "no se que decirle whatever";
        }else{

            return "> "+mensaje;
        }*/
    }

    @GET
    @Path("/{nombre}")
    public String saludo(@PathParam("nombre") String nombre){
        return "hola, " +nombre;
    }

    @GET
    @Path("/{nombre}/mayusculas")
    public String gritar(@PathParam("nombre") String nombre){
        return "hola, " +nombre.toUpperCase();
    }


}
