package com.portfolio.stockcontrol_api_springboot.exception;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException(String name) {
        super("Category with name '" + name + "' already exists");
    }
}
