package lt.viko.eif.asilaikis.bookstore_REST.model;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.util.List;

@XmlRootElement(name = "OrderList")
@XmlSeeAlso({Order.class, Client.class, Book.class})
public class GenericList <T> {
    private List<T> Value;
    public GenericList() {}
    @XmlAnyElement(lax = true)
    public List<T> getValue() {
        return Value;
    }
    public void setValue(List<T> Value) {
        this.Value = Value;
    }
}