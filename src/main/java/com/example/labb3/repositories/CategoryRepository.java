package com.example.labb3.repositories;

import com.example.labb3.entities.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface CategoryRepository extends ListCrudRepository<Category, UUID> {
    Category findCategoryByName(String name);
}
