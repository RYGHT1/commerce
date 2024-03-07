package com.emer.commerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.emer.commerce.custom.SoftDeleteRepository;
import com.emer.commerce.domain.Product;

@Repository
public interface ProductRepository extends SoftDeleteRepository<Product, Long> {

    Optional<Product> findByInventoryId(Long id);

    List<Product> findAllByCategoryId(Long id);
}
