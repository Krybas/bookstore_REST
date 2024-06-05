package lt.viko.eif.asilaikis.bookstore_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.asilaikis.bookstore_REST.assembler.BookAssembl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Book;
import lt.viko.eif.asilaikis.bookstore_REST.service.BookServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookControl {
    @Autowired
    private BookServ bookService;
    @Autowired
    private BookAssembl bookAssembler;

    @Operation(summary="Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found All Books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @GetMapping()
    public ResponseEntity<?> all()
    {
        List<Book> book = bookService.GetAllBooks();
        bookAssembler.Ref="All";
        return ResponseEntity.ok(bookAssembler.toModelList(book));
    }

    @Operation(summary = "Find book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@RequestParam(value = "id", required = false) long id) {
        try {
            Book book = bookService.getBookById(id);
            bookAssembler.Ref = "findID";
            return ResponseEntity.ok(bookAssembler.toModel(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"bookName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByName(@RequestParam(value = "bookName", required = false) String bookName) {
        try {
            List<Book> book = bookService.findByBookName(bookName);
            bookAssembler.Ref = "findBookName";
            return ResponseEntity.ok(bookAssembler.toModelList(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"firstName", "lastName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByAuthor(@RequestParam(value = "firstName", required = false) String firstName,
                                         @RequestParam(value = "lastName", required = false) String lastName) {
        try {
            List<Book> book = bookService.findByAuthorFirstNameAndLastName(firstName, lastName);
            bookAssembler.Ref = "findAuthor";
            return ResponseEntity.ok(bookAssembler.toModelList(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"firstName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByAuthorFirstName(@RequestParam(value = "firstName", required = false) String firstName) {
        try {
            List<Book> book = bookService.findByAuthorFirstName(firstName);
            bookAssembler.Ref = "findAuthorFirstName";
            return ResponseEntity.ok(bookAssembler.toModelList(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"lastName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByAuthorLastName(@RequestParam(value = "lastName", required = false) String lastName) {
        try {
            List<Book> book = bookService.findByAuthorLastName(lastName);
            bookAssembler.Ref = "findAuthorLastName";
            return ResponseEntity.ok(bookAssembler.toModelList(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "New Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"bookName", "firstName", "lastName", "price"}, method = RequestMethod.POST)
    public ResponseEntity<?> newBook(@RequestParam(name = "bookName") String bookName,
                                        @RequestParam(name = "firstName") String firstName,
                                        @RequestParam(name = "lastName") String lastName,
                                        @RequestParam(name = "price") String price) {
        Book book = bookService.newBook(bookName, firstName, lastName, price);
        bookAssembler.Ref = "New";
        return ResponseEntity.ok(bookAssembler.toModel(book));
    }

    @Operation(summary = "Delete Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBookByID(@RequestParam(value = "id", required = false) long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))})
    })
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestParam(value = "id") long id,
                                           @RequestParam(value = "bookName", required = false) String bookName,
                                           @RequestParam(value = "firstName", required = false) String firstName,
                                           @RequestParam(value = "lastName", required = false) String lastName,
                                           @RequestParam(value = "price", required = false) String price) {
        try {
            Book book = bookService.updateBook(id, bookName, firstName, lastName, price);
            bookAssembler.Ref = "Update";
            return ResponseEntity.ok(bookAssembler.toModel(book));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
