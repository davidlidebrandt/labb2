package com.example.labb3.services;

import com.example.labb3.entities.Category;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryMapper> getAllCategories() {
        return categoryRepository.findAll().stream().map(
                (category) -> {return new CategoryMapper(category.getId(), category.getName(), category.getSymbol(), category.getDescription());
                }
        ).toList();
    }

    public String addCategory() {

        return "Category added";
    }


}
