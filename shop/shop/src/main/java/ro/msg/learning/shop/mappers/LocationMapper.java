package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Location;

@Component
@RequiredArgsConstructor

public class LocationMapper {

    public LocationDTO locationToLocationDTO(Location location) {
        return LocationDTO.builder()
                .name(location.getName())
                .city(location.getCity())
                .country(location.getCountry())
                .street(location.getStreet())
                .county(location.getCounty())
                .build();
    }

    public Location locationDtoToLocation(LocationDTO locationDTO) {
        return Location.builder()
                .name(locationDTO.getName())
                .city(locationDTO.getCity())
                .country(locationDTO.getCountry())
                .street(locationDTO.getStreet())
                .county(locationDTO.getCounty())
                .build();
    }
}
