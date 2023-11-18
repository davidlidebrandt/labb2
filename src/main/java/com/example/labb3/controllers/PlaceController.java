package com.example.labb3.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/place")
public class PlaceController {

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
