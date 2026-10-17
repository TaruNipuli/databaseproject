
package fi.metropolia.tarunip.database_projects.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents an order in the database
@Entity
@Table(name = "orders")
public class Order {

    // Generates the order ID automatically
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Stores the products and quantities belonging to this order
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Customer who placed the order
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Date and time when the order was placed
    private LocalDateTime orderDate;

    // Converts order status between enum and database text
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Returns the items in this order
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    // Adds an item and connects it to this order
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }
}
