package com.example.labb3.controllers;

import com.example.labb3.entities.Category;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<CategoryMapper> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("{name}")
    public Optional<Category> getCategory(@PathVariable String name) {
        return categoryService.getCategory(name);
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
