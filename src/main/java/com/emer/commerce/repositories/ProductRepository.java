package com.emer.commerce.repositories;

import org.springframework.stereotype.Repository;

import com.emer.commerce.custom.SoftDeleteRepository;
import com.emer.commerce.domain.Product;

@Repository
public interface ProductRepository extends SoftDeleteRepository<Product, Long> {

}
