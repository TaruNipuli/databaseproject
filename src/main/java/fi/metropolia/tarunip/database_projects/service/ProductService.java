package fi.metropolia.tarunip.database_projects.service;

import fi.metropolia.tarunip.database_projects.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fi.metropolia.tarunip.database_projects.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.List;

// Provides product operations
@Service
public class ProductService {

    private final ProductRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public int increasePricesByCategory(Integer categoryId) {
        return repository.increasePricesByCategory(categoryId);
    }

    public List<Product> searchProducts(BigDecimal minPrice, Integer minStock) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        query.select(product)
                .where(
                        cb.greaterThanOrEqualTo(product.get("price"), minPrice),
                        cb.greaterThanOrEqualTo(product.get("stock_quantity"), minStock)
                );

        return entityManager.createQuery(query).getResultList();
    }
}




