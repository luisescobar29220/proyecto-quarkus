package quarkus.genres;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genres")
@Transactional
public class GenreResource {

    @Inject
    private GenreRepository genres;

    @GET
    public PaginatedResponse<Genre> list(
            @QueryParam("page") @DefaultValue("1") int page){

        Page p = new Page(page -1,5);
        var query = genres.findAll(Sort.descending("createAt")).page(p);
        return new PaginatedResponse<>(query);
    }

    @POST
    public Response create(Genre genre){
        genres.persist(genre);
        return Response.created(URI.create("/genres/" + genre.getId())).entity(genre).build();
    }

    @GET
    @Path("{id}")
    public Genre get(@PathParam("id") Long id){
        return genres
                .findByIdOptional(id)
                .orElseThrow(()-> new NoSuchElementException("Genre " +id+ "not found"));
    }

    @PUT
    @Path("{id}")
    public Genre update(@PathParam("id") Long id, Genre inbox){
        Genre found = genres
                .findByIdOptional(id)
                .orElseThrow(()-> new NoSuchElementException("Genre " +id+ "not found"));
        found.setName(inbox.getName());
        genres.persist(found);
        return found;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        genres.deleteById(id);
    }


}
