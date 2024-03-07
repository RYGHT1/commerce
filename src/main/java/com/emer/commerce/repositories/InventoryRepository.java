package com.emer.commerce.repositories;

import org.springframework.stereotype.Repository;

import com.emer.commerce.custom.SoftDeleteRepository;
import com.emer.commerce.domain.Inventory;

@Repository
public interface InventoryRepository extends SoftDeleteRepository<Inventory, Long> {

}
