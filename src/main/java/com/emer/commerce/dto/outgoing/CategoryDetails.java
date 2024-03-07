package com.emer.commerce.dto.outgoing;

import com.emer.commerce.domain.Category;

import lombok.Data;

@Data
public class CategoryDetails {

    private Long id;
    private String name;
    private String description;
    
    public CategoryDetails(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
    
}
