package com.emer.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Inventory;
import com.emer.commerce.dto.outgoing.InventoryDetails;
import com.emer.commerce.repositories.InventoryRepository;

import jakarta.validation.constraints.Positive;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;
    ProductService productService;

    @Autowired
    public InventoryService(ProductService productService, InventoryRepository inventoryRepository) {
        this.productService = productService;
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory create(Integer inventoryCount) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryCount);
        return inventoryRepository.save(inventory);
    }

    public List<InventoryDetails> findAll() {
        return inventoryRepository
                .findAll()
                .stream()
                .map(inv -> new InventoryDetails(inv, productService.findProductByInventory(inv.getId())))
                .toList();
    }

    public InventoryDetails findById(Long id) {
        return new InventoryDetails(inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found by id: " + id)),
                productService.findProductByInventory(id));
    }

    public void update(Long id, @Positive Integer quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found by id: " + id));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public void delete(Long id) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found by id: " + id));
        inventoryRepository.softDelete(inv);
    }

}
