package com.emer.commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.commerce.domain.Category;
import com.emer.commerce.dto.outgoing.CategoryDetails;
import com.emer.commerce.dto.outgoing.CategoryFull;
import com.emer.commerce.dto.outgoing.ProductDetails;
import com.emer.commerce.repositories.CategoryRepository;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    public List<CategoryDetails> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDetails::new)
                .toList();
    }

    public CategoryFull findById(Long id) {
        return convertToFull(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found by id: " + id)));
    }

    public CategoryFull findByName(String name) {
        return convertToFull(categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found by name: " + name)));
    }

    private CategoryFull convertToFull(Category category) {
        return new CategoryFull(category, productService.findAllProductsByCategoryId(category.getId()).stream()
                .map(ProductDetails::new)
                .toList());
    }

    public void delete(Long id, Optional<String> replacementName) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found by id: " + id));
        if (!productService.findAllProductsByCategoryId(id).isEmpty())
            replacementName.ifPresent(name -> {
                Category replacementCategory = categoryRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Category not found by name: " + name));
                productService.exchangeCategoryForAllProducts(id, replacementCategory);
            });
        categoryRepository.softDelete(category);
    }
}
