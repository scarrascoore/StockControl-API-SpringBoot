package com.portfolio.stockcontrol_api_springboot.exception;

import com.portfolio.stockcontrol_api_springboot.entity.StockMovementType;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(Long productId,Integer currentStock,Integer requestedQuantity)
    {
        super("Product with id " + productId +
                " has insufficient stock Current stock: " + currentStock +
                ", requested quantity: " + requestedQuantity
        );
    }
}
