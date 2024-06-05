package lt.viko.eif.asilaikis.bookstore_REST.assembler;

import lt.viko.eif.asilaikis.bookstore_REST.controller.ClientControl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientAssembl implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    public String Ref;
    public List<EntityModel<Client>> toModelList(List<Client> clients) {
        return clients.stream().map(this::toModel).collect(Collectors.toList());
    }
    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(ClientControl.class).all()).withRel("Get all clients")
                        : linkTo(methodOn(ClientControl.class).all()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(ClientControl.class).findByID(client.getId())).withRel("Find client")
                        : linkTo(methodOn(ClientControl.class).findByID(client.getId())).withSelfRel(),
                !Objects.equals(Ref, "findName") ? linkTo(methodOn(ClientControl.class).findByName(client.getFirstName(), client.getLastName())).withRel("Find client")
                        : linkTo(methodOn(ClientControl.class).findByName(client.getFirstName(), client.getLastName())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(ClientControl.class).newClient(client.getFirstName(), client.getLastName())).withRel("New Client")
                        : linkTo(methodOn(ClientControl.class).newClient(client.getFirstName(), client.getLastName())).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(ClientControl.class).deleteClientByID(client.getId())).withRel("Delete Client")
                        : linkTo(methodOn(ClientControl.class).deleteClientByID(client.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(ClientControl.class).updateClient(client.getId(), client.getFirstName(), client.getLastName())).withRel("Update Client")
                        : linkTo(methodOn(ClientControl.class).updateClient(client.getId(), client.getFirstName(), client.getLastName())).withSelfRel(),
                !Objects.equals(Ref, "findFirstName") ? linkTo(methodOn(ClientControl.class).findByFirstName(client.getFirstName())).withRel("Find client")
                        : linkTo(methodOn(ClientControl.class).findByFirstName(client.getFirstName())).withSelfRel(),
                !Objects.equals(Ref, "findLastName") ? linkTo(methodOn(ClientControl.class).findByLastName(client.getLastName())).withRel("Find client")
                        : linkTo(methodOn(ClientControl.class).findByLastName(client.getLastName())).withSelfRel());
    }
}
