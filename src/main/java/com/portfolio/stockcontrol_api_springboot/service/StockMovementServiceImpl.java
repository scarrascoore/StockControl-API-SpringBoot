package com.portfolio.stockcontrol_api_springboot.service;

import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.stock.StockMovementResponse;
import com.portfolio.stockcontrol_api_springboot.entity.Product;
import com.portfolio.stockcontrol_api_springboot.entity.StockMovement;
import com.portfolio.stockcontrol_api_springboot.entity.StockMovementType;
import com.portfolio.stockcontrol_api_springboot.exception.InsufficientStockException;
import com.portfolio.stockcontrol_api_springboot.exception.ProductNotFoundException;
import com.portfolio.stockcontrol_api_springboot.repository.ProductRepository;
import com.portfolio.stockcontrol_api_springboot.repository.StockMovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService{
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementServiceImpl(
            StockMovementRepository stockMovementRepository,
            ProductRepository productRepository
    ) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<StockMovementResponse> findAll() {
        return stockMovementRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<StockMovementResponse> findByProductId(Long productId) {
        return stockMovementRepository.findByProductIdOrderByCreatedAtDesc(productId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public StockMovementResponse create(StockMovementCreateRequest request) {
        Product product = findByProductOrThrow(request.productId());

        applyStockMovement(product,request.movementType(),request.quantity());

        StockMovement stockMovement = new StockMovement(
                product,
                request.movementType(),
                request.quantity(),
                request.reason()
        );

        StockMovement savedMovement = stockMovementRepository.save(stockMovement);
        productRepository.save(product);

        return  toResponse(savedMovement);

    }

    private void applyStockMovement(
            Product product,
            StockMovementType movementType,
            Integer quantity
    ) {
        if (movementType == StockMovementType.IN){
            product.increaseStock(quantity);
            return;
        }
        validateEnoughStock(product,quantity);
        product.decreaseStock(quantity);
    }

    private void validateEnoughStock(Product product, Integer requestQuantity) {
        if (product.getCurrentStock() < requestQuantity) {
            throw new InsufficientStockException(
                    product.getId(),
                    product.getCurrentStock(),
                    requestQuantity
            );
        }
    }

    private Product findByProductOrThrow(Long productId) {
        return  productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));
    }

    private StockMovementResponse toResponse(StockMovement stockMovement) {
        Product product = stockMovement.getProduct();

        return new StockMovementResponse(
                stockMovement.getId(),
                product.getId(),
                product.getName(),
                stockMovement.getMovementType(),
                stockMovement.getQuantity(),
                product.getCurrentStock(),
                stockMovement.getReason(),
                stockMovement.getCreatedAt()
        );
    }









}
