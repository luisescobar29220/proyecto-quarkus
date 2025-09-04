package quarkus.genres;

import io.quarkiverse.groovy.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
}
