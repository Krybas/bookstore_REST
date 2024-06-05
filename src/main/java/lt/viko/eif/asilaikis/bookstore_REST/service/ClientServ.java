package lt.viko.eif.asilaikis.bookstore_REST.service;

import lt.viko.eif.asilaikis.bookstore_REST.database.ClientRepo;
import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServ {

    @Autowired
    private ClientRepo clientRepo;

    public List<Client> GetAllClients() {
        return clientRepo.findAll();
    }
    public void deleteClientById(Long id) {
        clientRepo.deleteById(id);
    }
    public Client updateClient(Long id, String firstName, String lastName) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (firstName != null) {
            client.setFirstName(firstName);
        }
        if (lastName != null) {
            client.setLastName(lastName);
        }
        clientRepo.save(client);
        return client;
    }
    public Client getClientById(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

    }
    public Client newClient(String firstName, String lastName) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        return clientRepo.save(client);
    }
    public List<Client> findByName(String firstName, String lastName) {
            return clientRepo.findByFirstNameAndLastName(firstName, lastName)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
        }
    public List<Client> findByFirstName(String firstName) {
        return clientRepo.findByFirstName(firstName)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
    public List<Client> findByLastName(String lastName) {
        return clientRepo.findByLastName(lastName)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
