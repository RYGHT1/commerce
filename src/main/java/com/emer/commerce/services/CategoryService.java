package com.emer.commerce.services;

import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Category;
import com.emer.commerce.repositories.CategoryRepository;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

}

