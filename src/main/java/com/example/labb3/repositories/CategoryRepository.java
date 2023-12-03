package com.example.labb3.repositories;

import com.example.labb3.entities.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Category findCategoryByName(String name);
}
