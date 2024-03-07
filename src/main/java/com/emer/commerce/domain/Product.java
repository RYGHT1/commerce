package com.emer.commerce.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(unique = true)
    String SKU;

    @ManyToOne
    Category category;

    @OneToOne
    Inventory inventory;

    Double price;

    @OneToOne
    Discount discount;

    @Column(columnDefinition = "TIMESTAMP")

    LocalDateTime created_at;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime modified_at;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime deleted_at;
}
