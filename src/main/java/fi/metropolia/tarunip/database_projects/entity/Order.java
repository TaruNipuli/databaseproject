package fi.metropolia.tarunip.database_projects.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Convert;

import java.util.List;

// Represents an order in the database
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "orderitems",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    @JsonIgnore // Prevents recursive JSON output
    public List<Product> getProducts() {
        return products;
    }

    // Converts order status between enum and database text
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}










