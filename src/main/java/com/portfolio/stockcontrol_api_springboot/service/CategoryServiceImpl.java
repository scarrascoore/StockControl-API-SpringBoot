package com.portfolio.stockcontrol_api_springboot.service;

import com.portfolio.stockcontrol_api_springboot.dto.category.CategoryCreateRequest;
import com.portfolio.stockcontrol_api_springboot.dto.category.CategoryResponse;
import com.portfolio.stockcontrol_api_springboot.entity.Category;
import com.portfolio.stockcontrol_api_springboot.exception.CategoryNotFoundException;
import com.portfolio.stockcontrol_api_springboot.exception.DuplicateCategoryException;
import com.portfolio.stockcontrol_api_springboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = findCategoryOrThrow(id);
        return toResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        validateCategoryNameDoesNotExist(request.name());

        Category category = new Category(request.name(), request.description());
        Category savedCategory = categoryRepository.save(category);
        return toResponse(savedCategory);
    }

    private void validateCategoryNameDoesNotExist(String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateCategoryException(name);
        }
    }

    private Category findCategoryOrThrow(Long id) {
        return categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }


}
