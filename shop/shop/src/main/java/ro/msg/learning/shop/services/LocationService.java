package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
       public List<Location> findAll() {return locationRepository.findAll();}

    public Location createLocation(Location inputLocation) {
            Location location = Location.builder()
                    .name(inputLocation.getName())
                    .city(inputLocation.getCity())
                    .country(inputLocation.getCountry())
                    .street(inputLocation.getStreet())
                    .county(inputLocation.getCounty())
                    .build();
            return locationRepository.save(location);
    }

    public Location findLocationById(Integer id) {
           return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public Location checkLocationPresence(Location inputLocation) {
        Optional<Location> searchedLocation = locationRepository.findByName(inputLocation.getName());
        Location location;
        if (searchedLocation.isPresent()) {
            location = searchedLocation.get();
        } else {
            location= new Location();
            location.setName(location.getName());
            location.setCity(location.getCity());
            location.setCountry(location.getCountry());
            location.setStreet(location.getStreet());
            location.setCounty(location.getCounty());
            locationRepository.save(location);
        }
        return location;
    }

}
