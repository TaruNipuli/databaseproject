
package fi.metropolia.tarunip.database_projects.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.math.BigDecimal;

// Represents one product in an order
@Entity
@Table(name = "orderitems")
public class OrderItem {

    // Uses the order ID and product ID as a composite key
    @EmbeddedId
    private OrderItemId id;

    // Links the order item to its order
    @ManyToOne
    @MapsId("order")
    @JoinColumn(name = "order_id")
    private Order order;

    // Links the order item to its product
    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product_id")
    private Product product;

    // Number of units ordered
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // Product price when the order was placed
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    // Required by JPA
    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

        // Creates the composite key using the generated order ID
        this.id = new OrderItemId(order.getId(), product.getId());
    }

    public OrderItemId getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
