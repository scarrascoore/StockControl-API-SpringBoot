package com.portfolio.stockcontrol_api_springboot.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(long id) {
        super("Category with id " + id + "was not found");
    }
}
