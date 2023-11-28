package com.example.labb3.controllers;

import com.example.labb3.entities.Test;
import com.example.labb3.mappers.TestMapper;
import com.example.labb3.services.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<TestMapper> getTest() {
        return testService.getAllTest();
    }

    @GetMapping("{id}")
    public int getID(@PathVariable int id) {
        return id;
    }

    @PostMapping
    public String addTest() {
        testService.addTest();
        return "Success";
    }
}
