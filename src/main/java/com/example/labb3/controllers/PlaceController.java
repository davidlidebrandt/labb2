package com.example.labb3.controllers;

import com.example.labb3.services.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/place")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @GetMapping
    public List<String> getAllPlaces() {
        return List.of("1", "2", "3");
    }

    @PostMapping
    public void addPlace() {

    }

    @PatchMapping
    public void updatePlace() {

    }

    @DeleteMapping
    public void deletePlace() {

    }
}
