package com.emer.commerce.repositories;

import com.emer.commerce.domain.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emer.commerce.custom.SoftDeleteRepository;
import com.emer.commerce.domain.Discount;

import java.util.List;

@Repository
public interface DiscountRepository extends SoftDeleteRepository<Discount, Long> {

    @Query("""
            SELECT p
            FROM Discount d
            LEFT JOIN Product p ON d.id = p.discount.id
            WHERE p.deleted_at IS NULL
            AND d.deleted_at IS NULL
            AND d.active = true
            """)
    List<Product> findAllActive();

    @Query("""
            SELECT d
            FROM Discount d
            LEFT JOIN Product p ON d.id = p.discount.id
            WHERE p.id = :id
                """)
    List<Discount> findHistoryForProduct(Long id);

}
