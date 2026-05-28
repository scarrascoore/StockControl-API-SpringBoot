package com.portfolio.stockcontrol_api_springboot.controller;

import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementResponse;
import com.portfolio.stockcontrol_api_springboot.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public ResponseEntity<List<StockMovementResponse>>findAll()
    {
        return ResponseEntity.ok(stockMovementService.findAll());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockMovementResponse>> findByProductId(
            @PathVariable Long productId)
    {
        return ResponseEntity.ok(stockMovementService.findByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<StockMovementResponse> create(
            @Valid @RequestBody StockMovementCreateRequest request
    ){
        StockMovementResponse response = stockMovementService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
