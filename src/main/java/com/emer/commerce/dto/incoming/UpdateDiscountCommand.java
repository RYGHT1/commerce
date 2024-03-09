package com.emer.commerce.dto.incoming;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Data;

@Data
public class UpdateDiscountCommand {

    private Optional<String> name;
    private Optional<String> description;
    private Optional<Double> percentage;
    private Optional<Boolean> active;
    private Optional<LocalDateTime> scheduled_start;
    private Optional<LocalDateTime> scheduled_end;
}

