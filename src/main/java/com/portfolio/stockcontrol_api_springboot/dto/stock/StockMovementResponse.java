package com.portfolio.stockcontrol_api_springboot.dto.stock;

import com.portfolio.stockcontrol_api_springboot.entity.StockMovementType;

import java.time.LocalDateTime;

public record StockMovementResponse(
        Long id,
        Long productId,
        String productName,
        StockMovementType movementType,
        Integer quantity,
        Integer currentStockAfterMovement,
        String reason,
        LocalDateTime createAt
) {
}
