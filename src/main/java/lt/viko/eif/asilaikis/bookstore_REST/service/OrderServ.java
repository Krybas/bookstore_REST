package lt.viko.eif.asilaikis.bookstore_REST.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import lt.viko.eif.asilaikis.bookstore_REST.database.BookRepo;
import lt.viko.eif.asilaikis.bookstore_REST.database.ClientRepo;
import lt.viko.eif.asilaikis.bookstore_REST.database.OrderRepo;
import lt.viko.eif.asilaikis.bookstore_REST.model.Book;
import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import lt.viko.eif.asilaikis.bookstore_REST.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServ {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private EntityManager entityManager;
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
    public void deleteOrderById(Long orderID) {
        orderRepo.deleteById(orderID);
    }

    @Transactional
    public Order insertOrder (Long book_id, Long client_id) throws Exception {
        try {
            Client client = clientRepo.findById(client_id)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            Book book = bookRepo.findById(book_id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            Order order = new Order();
            order.setBook(book);
            order.setClient(client);
            return orderRepo.save(order);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }
    public Order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    @Transactional
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "false")})
    public void updateOrder(Long id, Long book_id, Long client_id) throws Exception {
        Order updateOrder = getOrderById(id);
        entityManager.refresh(updateOrder);

        Client client = null;
        Book book = null;

        if (client_id != null) {
            client = clientRepo.findById(client_id)
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            entityManager.refresh(client);
        }
        if (book_id != null) {
            book = bookRepo.findById(book_id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            entityManager.refresh(book);
        }
        if (client != null) {
            updateOrder.setClient(client);
        }
        if (book != null) {
            updateOrder.setBook(book);
        }
        orderRepo.save(updateOrder);
    }
    public List<Order> findByBookName(String bookName) {
        return orderRepo.findByBookName(bookName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByClient(String firstName, String lastName) {
        return orderRepo.findByClient(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByClientFirstName(String firstName) {
        return orderRepo.findByClientFirstName(firstName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByClientLastName(String lastName) {
        return orderRepo.findByClientLastName(lastName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByBookAuthor(String firstName, String lastName) {
        return orderRepo.findByBookAuthor(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByAuthorFirstName(String firstName) {
        return orderRepo.findByAuthorFirstName(firstName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> findByAuthorLastName(String lastName) {
        return orderRepo.findByAuthorLastName(lastName)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
