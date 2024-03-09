package com.emer.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emer.commerce.dto.incoming.CreateDiscountCommand;
import com.emer.commerce.dto.outgoing.DiscountHistoryListItem;
import com.emer.commerce.dto.outgoing.DiscountListItem;
import com.emer.commerce.services.DiscountService;

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
