package quarkus.genres;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDto(

        @NotBlank
        String name
) {


}
