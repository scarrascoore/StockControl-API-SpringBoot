package com.portfolio.stockcontrol_api_springboot.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @NotBlank(message = "The product name is required")
        @Size(max = 150, message = "The product name must have a maximum of 150 characters")
        String name,

        @Size(max = 500, message = "The description must have a maximum of 500 characters")
        String description,

        @NotNull(message = "The price is required")
        @DecimalMin(value = "0.01",  message = "The price must be greater then zero")
        BigDecimal price,

        @NotNull(message = "The category is required")
        Long categoryID
) {
}
