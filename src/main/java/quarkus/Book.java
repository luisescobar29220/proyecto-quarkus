package quarkus;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String genre;

    private int numPages;

    private LocalDate pubDate;

    private String description;

    @CreationTimestamp
    private LocalDate createDate;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", numPages=" + numPages +
                ", pubDate=" + pubDate +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @UpdateTimestamp
    private LocalDate updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numPages == book.numPages && Objects.equals(title, book.title) && Objects.equals(pubDate, book.pubDate) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numPages, pubDate, description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    public void onCreate() {
        this.createDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateDate = LocalDate.now();
    }
}
