package fi.metropolia.tarunip.database_projects.controller;

import fi.metropolia.tarunip.database_projects.entity.ProductCategory;
import fi.metropolia.tarunip.database_projects.repository.ProductCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Provides GET endpoint for retrieving product categories
@RestController
@RequestMapping("/productcategories")
public class ProductCategoryController {

    private final ProductCategoryRepository repository;

    public ProductCategoryController(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProductCategory> getAllProductCategories() {
        return repository.findAll();
    }

    // Adds a new product category to the database.
    @PostMapping
    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {
        return repository.save(productCategory);
    }

}

