package com.emer.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Inventory;
import com.emer.commerce.repositories.InventoryRepository;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory create(Integer inventoryCount) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryCount);
        return inventoryRepository.save(inventory);
    }


}

