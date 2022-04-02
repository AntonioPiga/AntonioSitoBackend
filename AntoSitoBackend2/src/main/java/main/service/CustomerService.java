package main.service;

import main.model.dto.CustomerDto;
import main.model.entity.CustomerEntity;
import main.repository.CustomerRepository;
import main.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<Iterable<CustomerEntity>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<Object> getCustomerById(Long id) {

        return customerRepository.existsById(id) ? ResponseEntity.ok(customerRepository.findById(id)) : ResponseEntity.notFound().build();

    }

    public ResponseEntity<Object> insertCustomer(@RequestBody CustomerDto customerDto) {

        String emailToValidate = Validator.formalizeEmail(customerDto.getEmail());

        if(!Validator.emailIsValid(emailToValidate)) {
            return ResponseEntity.badRequest().body("Email must be in correct format");
        }
        customerRepository.save(new CustomerEntity(customerDto.getName(), customerDto.getEmail().toLowerCase().trim(), customerDto.getMessage()));

        return ResponseEntity.ok("Customer created");
    }

    public ResponseEntity<Object> updateCustomer(Long id, String name, String email, String message) {

        String emailToValidate = Validator.formalizeEmail(email);

        if(!Validator.emailIsValid(emailToValidate)) {
            return ResponseEntity.badRequest().body("Email must be in correct format");
        }

        if (customerRepository.existsById(id)) {
            CustomerEntity customer = new CustomerEntity(id, name, email, message);
            customerRepository.save(customer);
            return ResponseEntity.ok("Customer updated!");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> deleteCustomerById(Long id) {

        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok("customer deleted");
        }
        return ResponseEntity.notFound().build();
    }

}
