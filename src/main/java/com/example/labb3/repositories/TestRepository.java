package com.example.labb3.repositories;

import com.example.labb3.entities.Test;
import org.springframework.data.repository.ListCrudRepository;

public interface TestRepository extends ListCrudRepository<Test,Integer> {
}
