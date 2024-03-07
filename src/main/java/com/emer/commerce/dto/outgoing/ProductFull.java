package com.emer.commerce.dto.outgoing;

import com.emer.commerce.domain.Product;

import lombok.Data;

@Data
public class ProductFull {

    private Long id;
    private String name;
    private String description;
    private String SKU;
    private String categoryName;
    private Integer inventoryCount;
    private Double price;
    private Double discount;

    public ProductFull(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.SKU = product.getSKU();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
        this.inventoryCount = product.getInventory().getQuantity();
        this.discount = 0.0;
        if (product.getDiscount() != null)
            this.discount = product.getDiscount().getActive() ? product.getDiscount().getDiscount_percent() : 0.0;
    }
}
