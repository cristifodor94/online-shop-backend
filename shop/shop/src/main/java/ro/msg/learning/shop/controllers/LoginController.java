package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.CustomerDTO;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.mappers.CustomerMapper;
import ro.msg.learning.shop.services.CustomerService;

@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<CustomerDTO> login(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.findByUsernameAndPassword(customerDTO.getUsername(), customerDTO.getPassword());
        customerDTO = customerMapper.customerToCustomerDTO(customer);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
