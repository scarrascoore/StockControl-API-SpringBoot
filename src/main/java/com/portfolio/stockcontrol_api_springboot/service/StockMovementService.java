package com.portfolio.stockcontrol_api_springboot.service;

import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementResponse;

import java.util.List;

public interface StockMovementService {
    List<StockMovementResponse> findAll();
    List<StockMovementResponse> findByProductId(Long productId);
    StockMovementResponse create(StockMovementCreateRequest request);
}
