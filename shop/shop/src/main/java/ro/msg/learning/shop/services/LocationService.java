package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location createLocation(Location inputLocation) {
        return locationRepository.save(inputLocation);
    }

    public Location findLocationById(Integer id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
    }
}
