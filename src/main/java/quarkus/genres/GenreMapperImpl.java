package quarkus.genres;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre fromCreate(CreateGenreDto dto){
        var g = new Genre();
        g.setName(dto.name());
        return g;
    }

    @Override
    public void update(UpdateGenreDto dto, Genre genre) {
        genre.setName(dto.name());
    }
}
