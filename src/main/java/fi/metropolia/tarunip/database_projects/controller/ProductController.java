package fi.metropolia.tarunip.database_projects.controller;

import fi.metropolia.tarunip.database_projects.entity.Product;
import fi.metropolia.tarunip.database_projects.entity.ProductCategory;
import fi.metropolia.tarunip.database_projects.repository.ProductCategoryRepository;
import fi.metropolia.tarunip.database_projects.repository.ProductRepository;
import fi.metropolia.tarunip.database_projects.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

import java.util.List;

// Provides GET and POST endpoints for products.
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductService productService;

    // Had a problem saving the product category correctly ->
    // also need the category repository to find the existing category
    public ProductController(ProductRepository repository,
                             ProductCategoryRepository categoryRepository,
                             ProductService productService) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
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

    // Increases prices by 10% for all products in the selected category
    @PutMapping("/increase-prices/{categoryId}")
    public int increasePrices(@PathVariable Integer categoryId) {
        return productService.increasePricesByCategory(categoryId);
    }

    // Searches products using multiple conditions with Criteria API
    @GetMapping("/criteria-search")
    public List<Product> criteriaSearch(
            @RequestParam BigDecimal minPrice,
            @RequestParam Integer minStock) {

        return productService.searchProducts(minPrice, minStock);
    }

    // Updates an existing product (name, description, price, stock quantity) by its ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        Product product = repository.findById(id).orElseThrow();

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock_quantity(updatedProduct.getStock_quantity());

        return repository.save(product);
    }
}













