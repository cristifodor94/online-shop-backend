package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Location;

@Component
public class LocationMapper {

    public LocationDTO locationToLocationDTO(Location location) {
        return LocationDTO.builder()
                .name(location.getName())
                .address(location.getAddress())
                .build();
    }

    public Location locationDtoToLocation(LocationDTO locationDTO) {
        return Location.builder()
                .name(locationDTO.getName())
                .address(locationDTO.getAddress())
                .build();
    }
}
