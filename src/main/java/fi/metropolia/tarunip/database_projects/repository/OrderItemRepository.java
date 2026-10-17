
package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.OrderItem;
import fi.metropolia.tarunip.database_projects.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for order items
public interface OrderItemRepository
        extends JpaRepository<OrderItem, OrderItemId> {
}
