package com.example.labb3.services;

import com.example.labb3.entities.Test;
import com.example.labb3.mappers.TestMapper;
import com.example.labb3.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestMapper> getAllTest() {
        return testRepository.findAll().stream().map((test) -> {
            return new TestMapper(test.getID());
        }).toList();
    }

    public void addTest() {
        var newTest = new Test(10);
        testRepository.save(newTest);
    }
}
