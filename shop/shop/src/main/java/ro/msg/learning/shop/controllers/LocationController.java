package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.mappers.LocationMapper;
import ro.msg.learning.shop.services.LocationService;

@RestController
@RequestMapping(value = "/locations")
@RequiredArgsConstructor

public class LocationController {
    private final LocationMapper locationMapper;
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        Location location = locationService.createLocation(locationMapper.locationDtoToLocation(locationDTO));
        LocationDTO dto = locationMapper.locationToLocationDto(location);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
