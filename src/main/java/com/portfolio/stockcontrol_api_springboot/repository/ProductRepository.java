package com.portfolio.stockcontrol_api_springboot.repository;

import com.portfolio.stockcontrol_api_springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product>findByCategory_Id(Long categoryId);

}
