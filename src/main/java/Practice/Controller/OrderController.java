package Practice.Controller;
import Practice.Model.Order;
import Practice.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(service.createOrder(order), HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public List<Order> getAll() {
        return service.getAllOrders();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Order getById(@PathVariable String id) {
        return service.getOrderById(id);
    }

    // UPDATE FULL
    @PutMapping("/{id}")
    public Order update(@PathVariable String id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }

    // PATCH STATUS
    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable String id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteOrder(id);
        return "Order deleted";
    }
}