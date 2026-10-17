
package fi.metropolia.tarunip.database_projects.entity;

// Contains the product and quantity requested in an order
public class OrderItemRequest {

    private Integer productId;
    private Integer quantity;

    public OrderItemRequest() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
