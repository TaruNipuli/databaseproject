
package fi.metropolia.tarunip.database_projects.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Identifies one product in one order using a composite key
@Embeddable
public class OrderItemId implements Serializable {

    // ID of the order
    private Integer order;

    // ID of the product
    private Integer product;

    // Required by JPA
    public OrderItemId() {
    }

    // Creates a key from the order ID and product ID
    public OrderItemId(Integer order, Integer product) {
        this.order = order;
        this.product = product;
    }

    // Checks whether two keys contain the same order and product IDs
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof OrderItemId)) {
            return false;
        }

        OrderItemId that = (OrderItemId) object;

        return Objects.equals(order, that.order)
                && Objects.equals(product, that.product);
    }

    // Generates a hash code based on both IDs
    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
