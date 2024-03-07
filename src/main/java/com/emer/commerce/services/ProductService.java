package com.emer.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
                .category(categoryService.findByName(command.getCategoryName()))
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
        command.getCategoryName().ifPresent(category -> product.setCategory(categoryService.findByName(category)));
        command.getPrice().ifPresent(product::setPrice);
        productRepository.save(product);
    }
}
