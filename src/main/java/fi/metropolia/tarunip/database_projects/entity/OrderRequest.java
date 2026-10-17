
package fi.metropolia.tarunip.database_projects.entity;

import java.util.List;

// Contains the customer and products in a new order
public class OrderRequest {

    private Integer customerId;
    private List<OrderItemRequest> items;

    public OrderRequest() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}
