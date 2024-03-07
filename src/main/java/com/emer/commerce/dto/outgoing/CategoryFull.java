package com.emer.commerce.dto.outgoing;

import java.util.List;

import com.emer.commerce.domain.Category;

import lombok.Data;

@Data
public class CategoryFull {

    private Long id;
    private String name;
    private String description;
    private List<ProductDetails> products;
    
    public CategoryFull(Category category, List<ProductDetails> products) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.products = products;
    }
    
}
