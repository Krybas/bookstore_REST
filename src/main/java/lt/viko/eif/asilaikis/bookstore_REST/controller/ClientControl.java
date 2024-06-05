package lt.viko.eif.asilaikis.bookstore_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.asilaikis.bookstore_REST.assembler.ClientAssembl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import lt.viko.eif.asilaikis.bookstore_REST.service.ClientServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientControl {

    @Autowired
    private ClientServ clientService;
    @Autowired
    private ClientAssembl clientAssembler;

    @Operation(summary="Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found All Clients",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @GetMapping()
    public ResponseEntity<?> all()
    {
        List<Client> client = clientService.GetAllClients();
        clientAssembler.Ref="All";
        return ResponseEntity.ok(clientAssembler.toModelList(client));
    }

    @Operation(summary = "Find client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@RequestParam(value = "id", required = false) long id) {
        try {
            Client client = clientService.getClientById(id);
            clientAssembler.Ref = "findID";
            return ResponseEntity.ok(clientAssembler.toModel(client));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"firstName", "lastName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByName(@RequestParam(value = "firstName", required = false) String firstName,
                                        @RequestParam(value = "lastName", required = false) String lastName) {
        try {
            List<Client> client = clientService.findByName(firstName, lastName);
            clientAssembler.Ref = "findName";
            return ResponseEntity.ok(clientAssembler.toModelList(client));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"firstName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByFirstName(@RequestParam(value = "firstName", required = false) String firstName) {
        try {
            List<Client> client = clientService.findByFirstName(firstName);
            clientAssembler.Ref = "findFirstName";
            return ResponseEntity.ok(clientAssembler.toModelList(client));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation (summary = "Find Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"lastName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByLastName(@RequestParam(value = "lastName", required = false) String lastName) {
        try {
            List<Client> client = clientService.findByLastName(lastName);
            clientAssembler.Ref = "findLastName";
            return ResponseEntity.ok(clientAssembler.toModelList(client));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "New Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"firstName", "lastName"}, method = RequestMethod.POST)
    public ResponseEntity<?> newClient(@RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "lastName") String lastName) {
        Client client = clientService.newClient(firstName, lastName);
        clientAssembler.Ref = "New";
        return ResponseEntity.ok(clientAssembler.toModel(client));
    }

    @Operation(summary = "Delete Client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClientByID(@RequestParam(value = "id") long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Client",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@RequestParam(value = "id") long id,
                                          @RequestParam(value = "firstName", required = false) String firstName,
                                          @RequestParam(value = "lastName", required = false) String lastName) {
        try {
            Client client = clientService.updateClient(id, firstName, lastName);
            clientAssembler.Ref = "Update";
            return ResponseEntity.ok(clientAssembler.toModel(client));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
