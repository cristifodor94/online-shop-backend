package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Customer;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final CustomerService customerService;

    public Customer loadByUsername(String username) {
        return customerService.findCustomerByUserName(username);
    }

}
