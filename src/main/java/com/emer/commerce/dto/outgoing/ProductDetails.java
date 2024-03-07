package com.emer.commerce.dto.outgoing;

import com.emer.commerce.domain.Product;

import lombok.Data;

@Data
public class ProductDetails {

    private Long id;
    private String name;
    private String description;

    public ProductDetails(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }
}
