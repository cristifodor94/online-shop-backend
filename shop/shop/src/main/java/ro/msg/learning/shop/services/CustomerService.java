package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.repositories.CustomerRepository;
import ro.msg.learning.shop.utils.Roles;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new NotFoundException("Customer not found");
    }

    public Customer findByUsernameAndPassword(String username, String password) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent() && passwordEncoder.matches(password, customer.get().getPassword())) {
            return customer.get();
        }
        throw new NotFoundException("Customer not found");
    }

    public Customer createCustomer(Customer inputCustomer) {
        inputCustomer.setRoles(Roles.CUSTOMER);
        inputCustomer.setPassword(passwordEncoder.encode(inputCustomer.getPassword()));
        return customerRepository.save(inputCustomer);
    }

    public Boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }
}
