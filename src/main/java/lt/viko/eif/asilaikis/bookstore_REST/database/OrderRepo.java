package lt.viko.eif.asilaikis.bookstore_REST.database;

import lt.viko.eif.asilaikis.bookstore_REST.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository <Order, Long>{
    @Query(value = "INSERT into Order o (o.id, o.books.id, o.clients.id) VALUES (:id, :book_id, :client_id)", nativeQuery = true)
    void insertOrder(@Param("id") Long id, @Param("book_id") Long book_id, @Param("client_id") Long client_id);

    @Query("UPDATE Order o SET o.book.id = :book_id, o.client.id = :client_id WHERE o.id = :id")
    void updateOrder(@Param("id") Long id, @Param("book_id") Long book_id, @Param("client_id") Long client_id);

    //Find by Client
    @Query("SELECT o FROM Order o JOIN o.client c WHERE c.firstName = :firstName AND c.lastName = :lastName")
    Optional<List<Order>> findByClient(@Param("firstName") String firstName, @Param("lastName") String lastName);
    //Find by Book
    @Query("SELECT o FROM Order o JOIN o.book b WHERE b.bookName = :bookName")
    Optional<List<Order>> findByBookName(@Param("bookName") String bookName);
    //Find by Book Author
    @Query("SELECT o FROM Order o JOIN o.book b WHERE b.firstName = :firstName AND b.lastName = :lastName")
    Optional<List<Order>> findByBookAuthor(@Param("firstName") String firstName, @Param("lastName") String lastName);
    //Find by Author first name
    @Query("SELECT o FROM Order o JOIN o.book b WHERE b.firstName = :firstName")
    Optional<List<Order>> findByAuthorFirstName(@Param("firstName") String firstName);
    //Find by Author last name
    @Query("SELECT o FROM Order o JOIN o.book b WHERE b.lastName = :lastName")
    Optional<List<Order>> findByAuthorLastName(@Param("lastName") String lastName);
    //Find by Client first name
    @Query("SELECT o FROM Order o JOIN o.client c WHERE c.firstName = :firstName")
    Optional<List<Order>> findByClientFirstName(@Param("firstName") String firstName);
    //Find by Client last name
    @Query("SELECT o FROM Order o JOIN o.client c WHERE c.lastName = :lastName")
    Optional<List<Order>> findByClientLastName(@Param("lastName") String lastName);
}
