package lt.viko.eif.asilaikis.bookstore_REST.database;

import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository <Client, Long>{
    Optional<List<Client>> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<List<Client>> findByFirstName(String firstName);
    Optional<List<Client>> findByLastName(String lastName);

}

