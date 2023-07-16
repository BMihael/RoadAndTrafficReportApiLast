package com.example.ratra.service.impl;

import com.example.ratra.exception.LocationNotFoundException;
import com.example.ratra.mapper.MapStructMapper;
import com.example.ratra.model.Location;
import com.example.ratra.model.dto.LocationDto;
import com.example.ratra.model.form.LocationBoundsForm;
import com.example.ratra.model.response.ResponseMessages;
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
    public List<LocationDto> getLocation(LocationBoundsForm locationBoundsForm) {
        Double to_latitude = locationBoundsForm.getLatitudeToNorthWest();
        Double from_latitude = locationBoundsForm.getLatitudeFromSouthEast();

        Double from_longitude = locationBoundsForm.getLongitudeFromNorthWest();
        Double to_longitude = locationBoundsForm.getLongitudeToSouthEast();

        List<Location> locations = repository.findLocationsByBounds(
                to_latitude, from_latitude, from_longitude, to_longitude);

        List<LocationDto> locationDtoList = mapper.locationListToLocationDtoList(locations);

        log.info("Sending locations");
        return locationDtoList;
    }

    @Override
    public Location getLocationBySubmitId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new LocationNotFoundException(ResponseMessages.LOCATION_NOT_FOUND));
    }
}
