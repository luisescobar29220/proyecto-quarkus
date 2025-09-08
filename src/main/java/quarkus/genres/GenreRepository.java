package quarkus.genres;

import io.quarkiverse.groovy.hibernate.orm.panache.PanacheQuery;
import io.quarkiverse.groovy.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.PaginatedResponse;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {

    public PanacheQuery<Genre> findPage(int page){
        Page p = new Page(page -1,5);

        var query = findAll(Sort.descending("createAt"));
        query.page(p);
        return query;
    }
}
