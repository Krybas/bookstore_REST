package lt.viko.eif.asilaikis.bookstore_REST.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "firstName", "lastName"})
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    String firstName;
    String lastName;

    public Client()
    {

    }

    @Override
    public String toString()
    {
        return String.format("\tClient: \n" +
                        "\t\tFirst name: %s \n" +
                        "\t\tLast name: %s \n",
                this.firstName, this.lastName);
    }

    public Client(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @XmlElement(name = "First_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "Last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }
    //@XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
}