
package fi.metropolia.tarunip.database_projects.controller;

import fi.metropolia.tarunip.database_projects.entity.Order;
import fi.metropolia.tarunip.database_projects.entity.OrderRequest;
import fi.metropolia.tarunip.database_projects.entity.OrderStatus;
import fi.metropolia.tarunip.database_projects.repository.OrderRepository;
import fi.metropolia.tarunip.database_projects.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Provides endpoints for orders
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;
    private final OrderService orderService;

    public OrderController(
            OrderRepository repository,
            OrderService orderService) {
        this.repository = repository;
        this.orderService = orderService;
    }

    // Returns all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // Returns all orders belonging to a customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(
            @PathVariable Integer customerId) {
        return repository.findByCustomerId(customerId);
    }

    // Creates a new order
    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    // Updates the status of an order
    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Integer id,
            @RequestBody OrderStatus status) {
        Order order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        return repository.save(order);
    }
}
