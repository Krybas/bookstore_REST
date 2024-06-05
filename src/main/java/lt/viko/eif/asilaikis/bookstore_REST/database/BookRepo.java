package lt.viko.eif.asilaikis.bookstore_REST.database;

import lt.viko.eif.asilaikis.bookstore_REST.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository <Book, Long>{
    Optional<List<Book>> findByBookName(String bookName);
    Optional<List<Book>> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<List<Book>> findByFirstName(String firstName);
    Optional<List<Book>> findByLastName(String lastName);

}
