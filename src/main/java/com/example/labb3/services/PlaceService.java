package com.example.labb3.services;

import com.example.labb3.entities.Category;
import com.example.labb3.entities.Place;
import com.example.labb3.mappers.PlaceMapper;
import com.example.labb3.repositories.PlaceRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceMapper> getAllPlaces() {
        return placeRepository.findAll().stream().map((place -> {
            return new PlaceMapper(place.getId(), place.getName(), place.getUserId(), place.getCategory(), place.getVisibility(), place.getLastModified(), place.getDescription(), place.getCoordinate(), place.getCreated());
        })).toList();
    }

    public void addPlace(Place place) {
        place.setLastModified(LocalDateTime.now());
        placeRepository.save(place);
    }
}
