package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.CustomerDTO;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.mappers.CustomerMapper;
import ro.msg.learning.shop.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(customerMapper.customerDtoToCustomer(customerDTO));
        CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping({"/{username}"})
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(customerService.existsByUsername(username), HttpStatus.OK);
    }
}
