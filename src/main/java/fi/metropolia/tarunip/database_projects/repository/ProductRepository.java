package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for product
public interface ProductRepository extends JpaRepository<Product, Integer> {
}