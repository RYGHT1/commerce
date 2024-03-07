package com.emer.commerce.dto.incoming;

import lombok.Data;

@Data
public class addProductCommand {

    String name;
    String description;
    String SKU;
    String categoryName;
    Integer inventoryCount;
    Double price;
}
