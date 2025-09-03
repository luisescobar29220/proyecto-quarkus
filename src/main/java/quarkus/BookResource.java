package quarkus;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    @GET
    public List<Book> index(){
        return bookRepository.listAll();
    }

    @POST
    public Book insert(Book insertedBook){
        bookRepository.persist(insertedBook);
        return insertedBook;
    }

    @GET
    @Path("{id}")
    public Book retrieve(@PathParam("id") Long id){
        var book = bookRepository.findById(id);
        if(book != null){
            return book;
        }
        throw new NoSuchElementException("no hay libro con el ID " + ".");
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Long id){
        if (bookRepository.deleteById(id)){
            return "se ha borrado bien";
        } else{
            return "no se ha podido borrar";
        }
    }

    @PUT
    @Path("{id}")
    public Book udpdate(@PathParam("id") Long id ,Book book ){
        var updatedBook = bookRepository.findById(id);
        if(updatedBook != null) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setGenre(book.getGenre());
            updatedBook.setPubDate(book.getPubDate());
            updatedBook.setNumPages(book.getNumPages());
            updatedBook.setDescription(book.getDescription());
            bookRepository.persist(updatedBook);
            return updatedBook;
        }
        throw new NoSuchElementException("no hay libro con el ID " + ".");
    }
}
