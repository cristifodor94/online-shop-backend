package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.AddressDTO;
import ro.msg.learning.shop.entities.Address;

@Component
public class AddressMapper {
    public Address addressDtoToAddress(AddressDTO addressDTO) {
        return Address.builder()
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .street(addressDTO.getStreet())
                .county(addressDTO.getCounty())
                .build();
    }

    public AddressDTO addressToAddressDTO(Address address) {
        return AddressDTO.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .county(address.getCounty())
                .build();
    }
}
