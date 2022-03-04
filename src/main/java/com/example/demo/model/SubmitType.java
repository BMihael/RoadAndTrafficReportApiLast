package com.example.demo.model;

public enum SubmitType {
    ROAD_ACCIDENT("Road accident"),
    ROAD_WORK("Road work");

    private final String name;

    SubmitType(String name) {
        this.name = name;
    }
}
