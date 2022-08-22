package com.example.ratra.service;

import com.example.ratra.model.Location;
import com.example.ratra.model.dto.LocationDto;
import com.example.ratra.model.form.LocationBounds;

import java.util.List;

public interface LocationService {
    List<LocationDto> getLocation(LocationBounds locationBounds);
    Location getLocationBySubmitId(Long id);
}
