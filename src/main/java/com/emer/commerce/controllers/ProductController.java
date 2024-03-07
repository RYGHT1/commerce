package com.emer.commerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.commerce.dto.incoming.addProductCommand;
import com.emer.commerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(addProductCommand command) {
        productService.add(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
