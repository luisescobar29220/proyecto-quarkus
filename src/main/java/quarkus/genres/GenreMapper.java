package quarkus.genres;

public interface GenreMapper {

    Genre fromCreate(CreateGenreDto dto );

    void update(UpdateGenreDto dto, Genre genre);
}
