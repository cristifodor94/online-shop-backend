package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.repositories.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomerByUserName(String username) {
        return customerRepository.findByUsername(username).get();
    }

    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }
}
