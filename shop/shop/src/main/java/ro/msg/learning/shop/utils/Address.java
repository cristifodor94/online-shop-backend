package ro.msg.learning.shop.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    public String country;
    public String city;
    public String street;
    @Override
    public String toString() {
        return "Country:" + country + "\n" +
                "City:" + city + "\n" +
                "Street:" + street + "\n";
    }
}


