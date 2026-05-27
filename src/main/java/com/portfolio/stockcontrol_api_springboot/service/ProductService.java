package com.portfolio.stockcontrol_api_springboot.service;

import com.portfolio.stockcontrol_api_springboot.dto.product.ProductCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.product.ProductResponse;
import com.portfolio.stockcontrol_api_springboot.dto.product.ProductUpdateRequest;
import com.portfolio.stockcontrol_api_springboot.entity.Product;

import java.util.*;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);

    List<ProductResponse> findByName(String name);
    List<ProductResponse> findByCategory_Id(Long categoryId);

    ProductResponse create(ProductCreateRequest request);
    ProductResponse update(Long id, ProductUpdateRequest request);

    void deleteById(Long id);
}
