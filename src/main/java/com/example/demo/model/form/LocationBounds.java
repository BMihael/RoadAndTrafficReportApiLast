package com.example.demo.model.form;

import lombok.Data;

@Data
public class LocationBounds {
    private Double latitudeToNorthWest;
    private Double latitudeFromSouthEast;

    private Double longitudeFromNorthWest;
    private Double longitudeToSouthEast;
}