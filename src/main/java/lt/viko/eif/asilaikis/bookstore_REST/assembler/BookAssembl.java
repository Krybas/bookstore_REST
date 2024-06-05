package lt.viko.eif.asilaikis.bookstore_REST.assembler;

import lt.viko.eif.asilaikis.bookstore_REST.controller.BookControl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookAssembl implements RepresentationModelAssembler<Book, EntityModel<Book>> {
    public String Ref;
    public List<EntityModel<Book>> toModelList(List<Book> books) {
        return books.stream().map(this::toModel).collect(Collectors.toList());
    }
    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(BookControl.class).all()).withRel("Get all books")
                        : linkTo(methodOn(BookControl.class).all()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(BookControl.class).findByID(book.getId())).withRel("Find book")
                        : linkTo(methodOn(BookControl.class).findByID(book.getId())).withSelfRel(),
                !Objects.equals(Ref, "findBookName") ? linkTo(methodOn(BookControl.class).findByName(book.getBookName())).withRel("Find book")
                        : linkTo(methodOn(BookControl.class).findByName(book.getBookName())).withSelfRel(),
                !Objects.equals(Ref, "findAuthor") ? linkTo(methodOn(BookControl.class).findByAuthor(book.getFirstName(), book.getLastName())).withRel("Find book")
                        : linkTo(methodOn(BookControl.class).findByAuthor(book.getFirstName(), book.getLastName())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(BookControl.class).newBook(book.getBookName(), book.getFirstName(), book.getLastName(), book.getPrice())).withRel("New Book")
                        : linkTo(methodOn(BookControl.class).newBook(book.getBookName(), book.getFirstName(), book.getLastName(), book.getPrice())).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(BookControl.class).deleteBookByID(book.getId())).withRel("Delete Book")
                        : linkTo(methodOn(BookControl.class).deleteBookByID(book.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(BookControl.class).updateBook(book.getId(), book.getBookName(), book.getFirstName(), book.getLastName(), book.getPrice())).withRel("Update Book")
                        : linkTo(methodOn(BookControl.class).updateBook(book.getId(), book.getBookName(), book.getFirstName(), book.getLastName(), book.getPrice())).withSelfRel(),
                !Objects.equals(Ref, "findAuthorFirstName") ? linkTo(methodOn(BookControl.class).findByAuthorFirstName(book.getFirstName())).withRel("Find book")
                        : linkTo(methodOn(BookControl.class).findByAuthorFirstName(book.getFirstName())).withSelfRel(),
                !Objects.equals(Ref, "findAuthorLastName") ? linkTo(methodOn(BookControl.class).findByAuthorLastName(book.getLastName())).withRel("Find book")
                        : linkTo(methodOn(BookControl.class).findByAuthorLastName(book.getLastName())).withSelfRel());
    }
}
