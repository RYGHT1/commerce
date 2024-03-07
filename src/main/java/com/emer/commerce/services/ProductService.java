package com.emer.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.emer.commerce.domain.Product;
import com.emer.commerce.dto.incoming.addProductCommand;
import com.emer.commerce.repositories.ProductRepository;

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

        Product product = new Product();
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setSKU(command.getSKU());
        product.setCategory(command.getCategoryName());
        product.setInventory(command.getInventoryCount());
        product.setPrice(command.getPrice());
    }
}
