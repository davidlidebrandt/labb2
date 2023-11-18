package com.example.labb3.controllers;

import com.example.labb3.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<String> getAllCategories() {
        return List.of("Fun", "work");
    }

    @GetMapping("{id}")
    public String getCategory(@PathVariable int id) {
        return List.of("Fun", "work").get(id);
    }

    @PostMapping
    public void addCategory() {

    }
}
