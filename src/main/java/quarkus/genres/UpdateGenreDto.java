package quarkus.genres;

import jakarta.validation.constraints.NotBlank;

public record UpdateGenreDto(

        @NotBlank
        String name
) {
}
