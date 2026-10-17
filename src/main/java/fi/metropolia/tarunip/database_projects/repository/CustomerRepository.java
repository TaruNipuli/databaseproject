
package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for customers
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
