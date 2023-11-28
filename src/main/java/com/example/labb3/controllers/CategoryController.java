package com.example.labb3.controllers;

import com.example.labb3.entities.Category;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{id}")
    public String getCategory(@PathVariable int id) {
        return List.of("Fun", "work").get(id);
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
