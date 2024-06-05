package lt.viko.eif.asilaikis.bookstore_REST.service;

import lt.viko.eif.asilaikis.bookstore_REST.database.BookRepo;
import lt.viko.eif.asilaikis.bookstore_REST.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServ {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> GetAllBooks() {
        return bookRepo.findAll();
    }

    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }

    public Book updateBook(Long id, String bookName, String firstName, String lastName, String price) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (bookName != null) {
            book.setBookName(bookName);
        }
        if (firstName != null) {
            book.setFirstName(firstName);
        }
        if (lastName != null) {
            book.setLastName(lastName);
        }
        if (price != null) {
            book.setPrice(price);
        }
        bookRepo.save(book);
        return book;
    }

    public Book newBook(String bookName, String firstName, String lastName, String price) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setFirstName(firstName);
        book.setLastName(lastName);
        book.setPrice(price);
        return bookRepo.save(book);
    }
    public List<Book> findByBookName(String bookName) {
        return bookRepo.findByBookName(bookName)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public List<Book> findByAuthorFirstNameAndLastName(String firstName, String lastName) {
            return bookRepo.findByFirstNameAndLastName(firstName, lastName)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    public List<Book> findByAuthorFirstName(String firstName) {
        return bookRepo.findByFirstName(firstName)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    public List<Book> findByAuthorLastName(String lastName) {
        return bookRepo.findByLastName(lastName)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

}
