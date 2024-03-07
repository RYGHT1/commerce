package com.emer.commerce.dto.outgoing;

import java.time.LocalDateTime;

import com.emer.commerce.domain.Inventory;
import com.emer.commerce.domain.Product;

import lombok.Data;

@Data
public class InventoryDetails {

    private Long id;
    private String productName;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public InventoryDetails(Inventory inventory, Product product) {
        this.id = inventory.getId();
        this.productName = product.getName();
        this.quantity = inventory.getQuantity();
        this.createdAt = inventory.getCreated_at();
        this.modifiedAt = inventory.getModified_at();
    }
}
