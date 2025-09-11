package quarkus.genres;

import io.quarkiverse.groovy.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genres")
@Transactional
public class GenreResource {


    private GenreRepository genres;

    private GenreMapper mapper;

    private GenreValidator validator;

    @Inject
    public GenreResource (GenreRepository genres , GenreMapper mapper, GenreValidator validator){
        this.genres = genres;
        this.mapper = mapper;
        this.validator = validator;
    }

    // nos permite tener la pagina en total que tenemos y la cantidad que hay en ellas
    @GET
    public PaginatedResponse<GenreResponseDto> list(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("q") String q)
    {
        PanacheQuery<Genre> query = genres.findPage(page);

        if(q != null){
            var nameLike = "%" + q + "%";
            query.filter("name.like", Parameters.with("name", nameLike));
        }
        PanacheQuery<GenreResponseDto> present = query.project(GenreResponseDto.class);
        return new PaginatedResponse<>(present);
    }

    @POST
    public Response create( CreateGenreDto genre){
        var error = this.validator.validateGenre(genre);
        if(error.isPresent()){
            var msg = error.get();
            return Response.status(400).entity(msg).build();
        }
        var entity = mapper.fromCreate(genre);
        genres.persist(entity);
        var representation = mapper.present(entity);
        return Response.created(URI.create("/genres/" + entity.getId())).entity(representation).build();
    }

    @GET
    @Path("{id}")
    public GenreResponseDto get(@PathParam("id") Long id){
        return genres
                .findByIdOptional(id)
                .map(mapper::present)
                .orElseThrow(()-> new NoSuchElementException("Genre " +id+ "not found"));
    }

    @PUT
    @Path("{id}")
    public GenreResponseDto update(@PathParam("id") Long id,@Valid UpdateGenreDto inbox){
        Genre found = genres
                .findByIdOptional(id)
                .orElseThrow(()-> new NoSuchElementException("Genre " +id+ "not found"));
        mapper.update(inbox,found);
        genres.persist(found);
        return mapper.present(found);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        genres.deleteById(id);
    }


}
