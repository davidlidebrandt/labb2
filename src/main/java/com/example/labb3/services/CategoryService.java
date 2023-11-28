package com.example.labb3.services;

import com.example.labb3.entities.Category;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Category> getCategory(String name) {
        return Optional.ofNullable(categoryRepository.findCategoryByName(name));
    }

    public String addCategory(Category category) {
        var newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setSymbol(category.getSymbol());
        newCategory.setDescription(category.getDescription());
        categoryRepository.save(newCategory);
        return "Category added";
    }


}
