package com.portfolio.stockcontrol_api_springboot.dto.stock;

import com.portfolio.stockcontrol_api_springboot.entity.StockMovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StockMovementCreateRequest(
        @NotNull(message = "The product id is required")
        Long productId,

        @NotNull(message = "The movement type is required")
        StockMovementType movementType,

        @NotNull(message = "the quantity is required")
        @Min(value = 1,message ="The quantity must be at least 1" )
        Integer quantity,

        @Size(max = 300, message = "The reason must have a maximum of 300 characters")
        String reason
) {
}
