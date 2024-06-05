package lt.viko.eif.asilaikis.bookstore_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lt.viko.eif.asilaikis.bookstore_REST.assembler.OrderAssembl;
import lt.viko.eif.asilaikis.bookstore_REST.model.Client;
import lt.viko.eif.asilaikis.bookstore_REST.model.Order;
import lt.viko.eif.asilaikis.bookstore_REST.service.OrderServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControl {
    @Autowired
    private OrderServ orderService;
    @Autowired
    private OrderAssembl orderAssembler;


    @Operation(summary="Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found All Orders",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))})
    })
    @GetMapping()
    public ResponseEntity<?> all()
    {
        List<Order> orders = orderService.getAllOrders();
        orderAssembler.Ref="All";
        return ResponseEntity.ok(orderAssembler.toModelList(orders));
    }

    @Operation(summary = "New order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(params = {"book_id", "client_id"}, method = RequestMethod.POST)
    public ResponseEntity<?> newOrder(@RequestParam(value = "book_id") long book_id,
                                      @RequestParam(value = "client_id") long client_id) {
        try {
            Order order = orderService.insertOrder(book_id, client_id);
            orderAssembler.Ref = "New";
            return ResponseEntity.ok(orderAssembler.toModel(order));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find by book name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(value = "/findByBookName", params ={"bookName"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByBookName(@RequestParam(value = "bookName") String bookName) {
        try {
            List<Order> order = orderService.findByBookName(bookName);
            orderAssembler.Ref = "findBook";
            return ResponseEntity.ok(orderAssembler.toModelList(order));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(value = "/findByBookAuthor", method = RequestMethod.GET)
    public ResponseEntity<?> findByBookAuthor(@RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "lastName", required = false) String lastName) {
        try {
            if (firstName != null && lastName != null) {
                List<Order> order = orderService.findByBookAuthor(firstName, lastName);
                orderAssembler.Ref = "findAuthor";
                return ResponseEntity.ok(orderAssembler.toModelList(order));
            } else if (firstName != null) {
                List<Order> order = orderService.findByAuthorFirstName(firstName);
                orderAssembler.Ref = "findAuthor";
                return ResponseEntity.ok(orderAssembler.toModelList(order));
            } else if (lastName != null) {
                List<Order> order = orderService.findByAuthorLastName(lastName);
                orderAssembler.Ref = "findAuthor";
                return ResponseEntity.ok(orderAssembler.toModelList(order));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find by client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(value = "/findByClient", method = RequestMethod.GET)
    public ResponseEntity<?> findByClientName(@RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName != null && lastName != null) {
            List<Order> order = orderService.findByClient(firstName, lastName);
            orderAssembler.Ref = "findClient";
            return ResponseEntity.ok(orderAssembler.toModelList(order));
        } else if (firstName != null) {
            List<Order> order = orderService.findByClientFirstName(firstName);
            orderAssembler.Ref = "findClient";
            return ResponseEntity.ok(orderAssembler.toModelList(order));
        } else if (lastName != null) {
            List<Order> order = orderService.findByClientLastName(lastName);
            orderAssembler.Ref = "findClient";
            return ResponseEntity.ok(orderAssembler.toModelList(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrder(@RequestParam(value = "id") long id,
                                         @RequestParam(value = "book_id", required = false) Long book_id,
                                         @RequestParam(value = "client_id", required = false) Long client_id) {
        try {
            orderService.updateOrder(id, book_id, client_id);
            Order updateOrder = orderService.getOrderById(id);
            orderAssembler.Ref = "Update";
            return ResponseEntity.ok(orderAssembler.toModel(updateOrder));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    public ResponseEntity<?> findByID(@RequestParam(value = "id") long id) {
        try {
            Order order = orderService.getOrderById(id);
            orderAssembler.Ref = "findID";
            return ResponseEntity.ok(orderAssembler.toModel(order));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))})
    })
    @RequestMapping(params = {"id"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteByID(@RequestParam(value = "id", required = false)long id) {
            orderService.deleteOrderById(id);
            return ResponseEntity.noContent().build();
    }
}
