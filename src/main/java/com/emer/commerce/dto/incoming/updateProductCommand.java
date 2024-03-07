package com.emer.commerce.dto.incoming;

import java.util.Optional;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class updateProductCommand {

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private Optional<String> name;

    private Optional<String> description;

    private Optional<String> SKU;

    private Optional<String> categoryName;

    @Positive(message = "Price must be greater than zero")
    private Optional<Double> price;
}
