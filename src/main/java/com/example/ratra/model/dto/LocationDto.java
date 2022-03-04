package com.example.ratra.model.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private SubmitForLocationDto submit;
}
