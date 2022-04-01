package main.service;

import main.model.dto.CustomerDto;
import main.model.entity.CustomerEntity;
import main.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void insertCustomer(@RequestBody CustomerDto customerDto) {

        customerRepository.save(new CustomerEntity(customerDto.getName(), customerDto.getEmail(), customerDto.getMessage()));

    }

    public Object updateCustomer(CustomerEntity customer) {

        if (customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
            return "Customer updated!";
        }
        else return new FileNotFoundException("Customer id not exist...").getMessage();
    }
}
