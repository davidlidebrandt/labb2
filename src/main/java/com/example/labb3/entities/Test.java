package com.example.labb3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Test {

    public Test() {

    }

    public Test(int age) {
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;

    private int age;

    public int getAge() {
        return age;
    }

    public Integer getID() {
        return ID;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
