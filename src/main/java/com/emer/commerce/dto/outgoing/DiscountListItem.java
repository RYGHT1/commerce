package com.emer.commerce.dto.outgoing;

import com.emer.commerce.domain.Discount;
import com.emer.commerce.domain.Product;

import lombok.Data;

@Data
public class DiscountListItem {

    private Long DiscountId;
    private Long ProductId;
    private String name;
    private String description;
    private double discount;
    private double originalPrice;

    public DiscountListItem(Product product) {
        Discount discount = product.getDiscount();
        this.DiscountId = discount.getId();
        this.ProductId = product.getId();
        this.name = discount.getName().isBlank() ? product.getName() : discount.getName();
        this.description = discount.getDescription().isBlank() ? product.getDescription() : discount.getDescription();
        this.discount = discount.getDiscount_percent();
        this.originalPrice = product.getPrice();
    }

}
