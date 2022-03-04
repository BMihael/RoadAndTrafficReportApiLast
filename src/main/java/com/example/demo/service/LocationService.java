package com.example.demo.service;

import com.example.demo.model.dto.LocationDto;
import com.example.demo.model.form.LocationBounds;

import java.util.List;

public interface LocationService {
    List<LocationDto> getLocation(LocationBounds locationBounds);
}
