package lt.viko.eif.asilaikis.bookstore_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "bookName", "firstName", "lastName", "price"})
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    String bookName;
    String firstName;
    String lastName;
    String price;

    public Book()
    {

    }

    @Override
    public String toString()
    {
        return String.format("\tBook: \n" +
                        "\t\tBook name: %s \n" +
                        "\t\tAuthor first name: %s \n"+
                        "\t\tAuthor last name: %s \n" +
                        "\t\tBook price: %s \n",
                this.bookName, this.firstName, this.lastName, this.price);
    }

    public Book(String bookName, String firstName, String lastName, String price){
        this.bookName = bookName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
    }

    @XmlElement(name = "Book_name")
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @XmlElement(name = "First_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "Last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "Price")
    public void setPrice(String price) {
        this.price = price;
    }

    //@XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPrice() {
        return price;
    }
}
