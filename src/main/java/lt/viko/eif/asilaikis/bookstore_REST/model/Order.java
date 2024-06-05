package lt.viko.eif.asilaikis.bookstore_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "client", "book"})
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(targetEntity = Client.class, cascade = CascadeType.ALL)
    private Client client;
    @OneToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    private Book book;

    public Order ()
    {

    }

    @Override
    public String toString()
    {
        return String.format("Order: \n" +
                        "%s \n" +
                        "%s \n",
                this.client, this.book);

    }
    public Order(Client client, Book book)
    {
        this.client = client;
        this.book = book;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}