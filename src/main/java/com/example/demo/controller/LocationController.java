package com.example.demo.controller;

import com.example.demo.model.form.LocationBounds;
import com.example.demo.model.dto.LocationDto;
import com.example.demo.service.impl.LocationServiceImpl;
import com.example.demo.util.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrl.API_PATH)
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {

    @Autowired
    LocationServiceImpl locationService;

    @PostMapping("/location")
    public ResponseEntity<List<LocationDto>> getLocation(@RequestBody LocationBounds locationBounds) {
        return ResponseEntity.ok(locationService.getLocation(locationBounds));
    }
}
