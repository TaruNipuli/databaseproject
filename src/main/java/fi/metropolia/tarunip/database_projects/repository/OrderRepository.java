
package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Returns all orders belonging to a customer
    List<Order> findByCustomerId(Integer customerId);
}
