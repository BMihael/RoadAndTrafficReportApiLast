package com.example.ratra.model.form;

import lombok.Data;

@Data
public class LocationBoundsForm {
    private Double latitudeToNorthWest;
    private Double latitudeFromSouthEast;

    private Double longitudeFromNorthWest;
    private Double longitudeToSouthEast;
}