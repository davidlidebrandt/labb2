package com.example.labb3.repositories;
import com.example.labb3.entities.Category;
import com.example.labb3.entities.Place;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PlaceRepository extends ListCrudRepository<Place, Long> {

    List<Place> findAllByCategory(Category category);
}
