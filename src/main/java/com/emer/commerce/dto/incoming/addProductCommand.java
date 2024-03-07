package com.emer.commerce.dto.incoming;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class addProductCommand {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "SKU is required")
    private String SKU;

    @NotBlank(message = "Category name is required")
    private String categoryName;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Inventory count is required")
    @Min(value = 0, message = "Inventory count must be greater than or equal to zero")
    private Integer inventoryCount;
}
