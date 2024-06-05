package lt.viko.eif.asilaikis.bookstore_REST.assembler;

import lt.viko.eif.asilaikis.bookstore_REST.controller.OrderControl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembl implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    public String Ref;
    public List<EntityModel<Order>> toModelList(List<Order> orders) {
        return orders.stream().map(this::toModel).collect(Collectors.toList());
    }
    @Override
    public EntityModel<Order>toModel(Order order) {
        return EntityModel.of(order,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(OrderControl.class).all()).withRel("Get all orders")
                        : linkTo(methodOn(OrderControl.class).all()).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(OrderControl.class).newOrder(order.getBook().getId(), order.getClient().getId())).withRel("New order")
                        : linkTo(methodOn(OrderControl.class).newOrder(order.getBook().getId(), order.getClient().getId())).withSelfRel(),
                !Objects.equals(Ref, "findBook") ? linkTo(methodOn(OrderControl.class).findByBookName(order.getBook().getBookName())).withRel("Find by book name")
                        : linkTo(methodOn(OrderControl.class).findByBookName(order.getBook().getBookName())).withSelfRel(),
                !Objects.equals(Ref, "findAuthor") ? linkTo(methodOn(OrderControl.class).findByBookAuthor(order.getBook().getFirstName(), order.getBook().getLastName())).withRel("Find by author")
                        : linkTo(methodOn(OrderControl.class).findByBookAuthor(order.getBook().getFirstName(), order.getBook().getLastName())).withSelfRel(),
                !Objects.equals(Ref, "findClient") ? linkTo(methodOn(OrderControl.class).findByClientName(order.getClient().getFirstName(), order.getClient().getLastName())).withRel("Find by client")
                        : linkTo(methodOn(OrderControl.class).findByClientName(order.getClient().getFirstName(), order.getClient().getLastName())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(OrderControl.class).updateOrder(order.getId(), order.getBook().getId(), order.getClient().getId())).withRel("Update order")
                        : linkTo(methodOn(OrderControl.class).updateOrder(order.getId(), order.getBook().getId(), order.getClient().getId())).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(OrderControl.class).findByID(order.getId())).withRel("Find by ID")
                        : linkTo(methodOn(OrderControl.class).findByID(order.getId())).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(OrderControl.class).deleteByID(order.getId())).withRel("Delete order")
                        : linkTo(methodOn(OrderControl.class).deleteByID(order.getId())).withSelfRel());
    }
}
