package com.emer.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.commerce.dto.incoming.CreateDiscountCommand;
import com.emer.commerce.dto.incoming.UpdateDiscountCommand;
import com.emer.commerce.dto.outgoing.DiscountHistoryListItem;
import com.emer.commerce.dto.outgoing.DiscountListItem;
import com.emer.commerce.services.DiscountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    
    DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("")
    public ResponseEntity<List<DiscountListItem>> getDiscounts() {
        return new ResponseEntity<>(discountService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountListItem> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(discountService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable("id") Long id) {
        discountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateDiscount(@PathVariable("id") Long id, @RequestBody UpdateDiscountCommand command) {
        discountService.update(id, command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/{productId}")
    public ResponseEntity<Void> createDiscount(@PathVariable("productId") Long productId, CreateDiscountCommand command) {
        discountService.create(productId, command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<DiscountHistoryListItem>> getDiscountsForProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(discountService.findAllForProduct(id), HttpStatus.OK);
    }


}
