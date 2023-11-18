package com.example.labb3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class Test {

    @GetMapping
    public List<String> getTest() {
        return List.of("1", "2", "3");
    }

    @GetMapping("{id}")
    public int getID(@PathVariable int id) {
        return id;
    }
}
