
package fi.metropolia.tarunip.database_projects.service;

import fi.metropolia.tarunip.database_projects.entity.Customer;
import fi.metropolia.tarunip.database_projects.entity.Order;
import fi.metropolia.tarunip.database_projects.entity.OrderItem;
import fi.metropolia.tarunip.database_projects.entity.OrderItemRequest;
import fi.metropolia.tarunip.database_projects.entity.OrderRequest;
import fi.metropolia.tarunip.database_projects.entity.OrderStatus;
import fi.metropolia.tarunip.database_projects.entity.Product;
import fi.metropolia.tarunip.database_projects.repository.CustomerRepository;
import fi.metropolia.tarunip.database_projects.repository.OrderRepository;
import fi.metropolia.tarunip.database_projects.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

// Handles the creation of orders
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    // Creates and saves a new order
    @Transactional
    public Order createOrder(OrderRequest request) {

        // Finds the customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Creates the order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        // Saves the order first to generate its ID
        order = orderRepository.save(order);

        // Adds each requested product to the order
        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            // Checks that the quantity is valid
            if (itemRequest.getQuantity() == null || itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            // Checks that enough products are in stock
            if (product.getStock_quantity() == null
                    || product.getStock_quantity() < itemRequest.getQuantity()) {
                throw new IllegalArgumentException("Not enough products in stock");
            }

            // Reduces the stock by the ordered quantity
            product.setStock_quantity(
                    product.getStock_quantity() - itemRequest.getQuantity());
            productRepository.save(product);

            // Stores the product and its price at the time of ordering
            OrderItem orderItem = new OrderItem(
                    order,
                    product,
                    itemRequest.getQuantity(),
                    product.getPrice()
            );

            order.addOrderItem(orderItem);
        }

        // Saves the order items
        return orderRepository.save(order);
    }
}
