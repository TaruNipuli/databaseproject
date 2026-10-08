package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Provides database operations for product
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query("""
            UPDATE Product p
            SET p.price = p.price * 1.10
            WHERE p.category.id = :categoryId
            """)
    int increasePricesByCategory(@Param("categoryId") Integer categoryId);
}