package com.emer.commerce.dto.incoming;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateDiscountCommand {

    @NotNull
    @Positive
    private Double percentage;

    private Optional<String> description;

    private Optional<String> name;

    private Optional<Boolean> active = Optional.of(true);
}
