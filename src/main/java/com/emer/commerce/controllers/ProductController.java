package com.emer.commerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.commerce.dto.incoming.addProductCommand;
import com.emer.commerce.dto.incoming.updateProductCommand;
import com.emer.commerce.dto.outgoing.ProductFull;
import com.emer.commerce.services.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid addProductCommand command) {
        productService.add(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<ProductFull>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFull> find(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,@RequestBody @Valid updateProductCommand command) {
        productService.update(id, command);
        return ResponseEntity.ok().build();
    }

}
