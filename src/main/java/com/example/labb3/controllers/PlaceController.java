package com.example.labb3.controllers;

import com.example.labb3.entities.Place;
import com.example.labb3.mappers.PlaceMapper;
import com.example.labb3.services.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/places")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @GetMapping
    public List<PlaceMapper> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @PostMapping
    public String addPlace(@RequestBody Place place) {
        placeService.addPlace(place);
        return "Place added";
    }

    @PatchMapping
    public void updatePlace() {

    }

    @DeleteMapping
    public void deletePlace() {

    }
}
