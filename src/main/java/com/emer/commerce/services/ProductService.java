package com.emer.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.emer.commerce.domain.Category;
import com.emer.commerce.domain.Product;
import com.emer.commerce.dto.incoming.addProductCommand;
import com.emer.commerce.dto.incoming.updateProductCommand;
import com.emer.commerce.dto.outgoing.ProductFull;
import com.emer.commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;
    InventoryService inventoryService;
    CategoryService categoryService;

    @Autowired
    public ProductService(InventoryService inventoryService, ProductRepository productRepository,
            CategoryService categoryService) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public void add(addProductCommand command) {
        Product product = Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .SKU(command.getSKU())
                .category(categoryService.findCategoryByName(command.getCategoryName()))
                .inventory(inventoryService.create(command.getInventoryCount()))
                .price(command.getPrice())
                .build();

        productRepository.save(product);
    }

    public ProductFull find(Long id) {
        return new ProductFull(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id)));
    }

    public void delete(Long id) {
        productRepository.softDelete(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id)));
    }

    public List<ProductFull> findAll() {
        return productRepository.findAll().stream()
                .map(ProductFull::new)
                .toList();
    }

    public void update(Long id, updateProductCommand command) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
        command.getName().ifPresent(product::setName);
        command.getDescription().ifPresent(product::setDescription);
        command.getSKU().ifPresent(product::setSKU);
        command.getCategoryName()
                .ifPresent(category -> product.setCategory(categoryService.findCategoryByName(category)));
        command.getPrice().ifPresent(product::setPrice);
        productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
    }

    public Product findProductByInventory(Long id) {
        return productRepository.findByInventoryId(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
    }

    public List<Product> findAllProductsByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id);
    }

    public void exchangeCategoryForAllProducts(List<Product> products, Category replacementCategory) {
        products.forEach(product -> product.setCategory(replacementCategory));
        productRepository.saveAll(products);
    }

    public Product findProductByDiscountId(Long id) {
        return productRepository.findByDiscountId(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
    }
}
