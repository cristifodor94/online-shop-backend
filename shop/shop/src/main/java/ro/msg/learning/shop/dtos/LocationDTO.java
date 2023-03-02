package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entities.Address;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Integer id;
    private String name;
    private Address address;
}
