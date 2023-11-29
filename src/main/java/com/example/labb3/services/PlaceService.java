package com.example.labb3.services;

import com.example.labb3.mappers.PlaceMapper;
import com.example.labb3.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
