package com.example.labb3.controllers;

import com.example.labb3.entities.Category;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable String name) {
        Optional<Category> category = categoryService.getCategory(name);
        if(category.isEmpty()) {
            return new ResponseEntity<>(category, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
