package quarkus.genres;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.ws.rs.PathParam;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@FilterDef(
        name = "name.like",
        parameters = @ParamDef(name = "name", type = String.class)
)
@Filter(
        name = "name.like",
        condition = "LOWER(name) LIKE LOWER(:name)")
public class Genre {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("genreName")//renombro el nombre de name a genreName
    @JsonAlias({"genreName","name"})// cuando quiera crear un genero puedo usar genreName o name
    private String name;

    @CreationTimestamp
    //@JsonIgnore
    private LocalDate createAt;

    @UpdateTimestamp
    //@JsonIgnore
    private LocalDate updateAt;

    public int getClassification(){
        return name.length();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(name, genre.name) && Objects.equals(createAt, genre.createAt) && Objects.equals(updateAt, genre.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
