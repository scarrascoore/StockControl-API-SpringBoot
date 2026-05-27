package com.portfolio.stockcontrol_api_springboot.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " was not found");
    }
}
