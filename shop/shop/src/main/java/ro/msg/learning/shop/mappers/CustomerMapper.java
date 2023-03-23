package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.CustomerDTO;
import ro.msg.learning.shop.entities.Customer;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    public Customer customerDtoToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .username(customerDTO.getUsername())
                .password(customerDTO.getPassword())
                .emailAddress(customerDTO.getEmailAddress())
                .roles(customerDTO.getRoles())
                .build();
    }

    public CustomerDTO customerToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .emailAddress(customer.getEmailAddress())
                .roles(customer.getRoles())
                .build();
    }
}
