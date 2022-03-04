package com.example.ratra.service.impl;

import com.example.ratra.mapper.MapStructMapper;
import com.example.ratra.model.Location;
import com.example.ratra.model.dto.LocationDto;
import com.example.ratra.model.form.LocationBounds;
import com.example.ratra.repository.LocationRepository;
import com.example.ratra.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository repository;

    @Autowired
    MapStructMapper mapper;

    @Override
    public List<LocationDto> getLocation(LocationBounds locationBounds) {
        Double to_latitude = locationBounds.getLatitudeToNorthWest();
        Double from_latitude = locationBounds.getLatitudeFromSouthEast();

        Double from_longitude = locationBounds.getLongitudeFromNorthWest();
        Double to_longitude = locationBounds.getLongitudeToSouthEast();

        List<Location> locations = repository.findLocationsByBounds(to_latitude,from_latitude,from_longitude,to_longitude);

        List<LocationDto> locationDtos = mapper.locationListToLocationDtoList(locations);

        log.info("Sending locations");
        return locationDtos;
    }
}
