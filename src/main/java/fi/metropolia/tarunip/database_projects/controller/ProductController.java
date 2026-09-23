package fi.metropolia.tarunip.database_projects.controller;

import fi.metropolia.tarunip.database_projects.entity.Product;
import fi.metropolia.tarunip.database_projects.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import fi.metropolia.tarunip.database_projects.repository.ProductCategoryRepository;

import fi.metropolia.tarunip.database_projects.entity.ProductCategory;



// Provides GET and POST endpoints for products.
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;

    // Had a problem saving the product category correctly ->
    // also need the category repository to find the existing category
    public ProductController(ProductRepository repository,
                             ProductCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    // Returns all products.
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Had a problem with category_id being saved as NULL ->
    // have to find the existing category from the database before saving the product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            ProductCategory category =
                    categoryRepository.findById(product.getCategory().getId()).orElse(null);

            product.setCategory(category);
        }

        return repository.save(product);
    }
}

