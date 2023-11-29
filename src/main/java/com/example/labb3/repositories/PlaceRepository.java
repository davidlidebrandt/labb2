package com.example.labb3.repositories;

import com.example.labb3.entities.Place;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PlaceRepository extends ListCrudRepository<Place, UUID> {
}
