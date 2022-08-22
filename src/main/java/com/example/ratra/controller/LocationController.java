package com.example.ratra.controller;

import com.example.ratra.model.form.LocationBounds;
import com.example.ratra.model.dto.LocationDto;
import com.example.ratra.service.impl.LocationServiceImpl;
import com.example.ratra.util.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiUrl.API_PATH)
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {

    @Autowired
    LocationServiceImpl locationService;

    @PostMapping("/location")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<LocationDto>> getLocation(@RequestBody LocationBounds locationBounds) {
        return ResponseEntity.ok(locationService.getLocation(locationBounds));
    }
}
