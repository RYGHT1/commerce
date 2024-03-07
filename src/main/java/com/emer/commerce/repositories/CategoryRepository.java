package com.emer.commerce.repositories;

import java.util.Optional;

import com.emer.commerce.custom.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

import com.emer.commerce.domain.Category;

@Repository
public interface CategoryRepository extends SoftDeleteRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
