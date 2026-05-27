package com.portfolio.stockcontrol_api_springboot.service;

import com.portfolio.stockcontrol_api_springboot.dto.product.ProductCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.product.ProductResponse;
import com.portfolio.stockcontrol_api_springboot.dto.product.ProductUpdateRequest;
import com.portfolio.stockcontrol_api_springboot.entity.Category;
import com.portfolio.stockcontrol_api_springboot.entity.Product;
import com.portfolio.stockcontrol_api_springboot.exception.CategoryNotFoundException;
import com.portfolio.stockcontrol_api_springboot.exception.ProductNotFoundException;
import com.portfolio.stockcontrol_api_springboot.repository.CategoryRepository;
import com.portfolio.stockcontrol_api_springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = findProductOrThrow(id);
        return toResponse(product);
    }

    @Override
    public List<ProductResponse> findByName(String name) {
        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findByCategory_Id(Long categoryId) {
        return productRepository
                .findByCategory_Id(categoryId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {
        Category category = findByCategoryOrThrow(request.categoryId());

        Product product = new Product(
                request.name(),
                request.description(),
                request.price(),
                category
        );
        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    @Override
    public ProductResponse update(Long id,ProductUpdateRequest request) {
        Product product = findProductOrThrow(id);
        Category category = findCategoryOrThrow(request.categoryID());

        product.update(
                request.name(),
                request.description(),
                request.price(),
                category
        );
        Product updatedProduct = productRepository.save(product);
        return toResponse(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
        Product product = findProductOrThrow(id);
        productRepository.delete(product);
    }

    private Product findProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private Category findCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCurrentStock(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

}





















