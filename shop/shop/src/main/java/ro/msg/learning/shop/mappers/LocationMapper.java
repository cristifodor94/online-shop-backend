package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Location;

@Component
public class LocationMapper {

    public Location locationDtoToLocation(LocationDTO locationDTO) {
        return Location.builder()
                .id(locationDTO.getId())
                .name(locationDTO.getName())
                .address(Address.builder()
                        .city(locationDTO.getCity())
                        .county(locationDTO.getCounty())
                        .street(locationDTO.getStreet())
                        .country(locationDTO.getCountry())
                        .build())
                .build();
    }

    public LocationDTO locationToLocationDto(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .city(location.getAddress().getCity())
                .street(location.getAddress().getStreet())
                .country(location.getAddress().getCountry())
                .county(location.getAddress().getCounty())
                .build();
    }
}
