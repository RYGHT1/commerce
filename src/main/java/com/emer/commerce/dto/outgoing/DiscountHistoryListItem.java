package com.emer.commerce.dto.outgoing;

import java.time.LocalDateTime;

import com.emer.commerce.domain.Discount;

import lombok.Data;

@Data
public class DiscountHistoryListItem {

    private long id;
    private String name;
    private String description;
    private double discount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public DiscountHistoryListItem(Discount discount) {
        this.id = discount.getId();
        this.name = discount.getName();
        this.description = discount.getDescription();
        this.discount = discount.getDiscount_percent();
        this.createdAt = discount.getCreated_at();
        this.modifiedAt = discount.getModified_at();
        this.deletedAt = discount.getDeleted_at();
    }
}
