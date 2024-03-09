package com.emer.commerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.commerce.dto.outgoing.InventoryDetails;
import com.emer.commerce.services.InventoryService;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    
    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDetails>> getAll() {
        return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDetails> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Positive Integer quantity) {
        inventoryService.update(id, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        inventoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
