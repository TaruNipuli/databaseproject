package fi.metropolia.tarunip.database_projects.repository;

import fi.metropolia.tarunip.database_projects.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides database operations for ProductCategory
// JpaRepository gives ready-made methods for reading, saving and deleting data

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}



