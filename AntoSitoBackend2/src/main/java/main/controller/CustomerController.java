package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import main.model.entity.CustomerEntity;
import main.model.dto.CustomerDto;
import main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("customers/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(
            description = "Insert new customer",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @PostMapping("insert")
    public void insertCustomer(@RequestBody CustomerDto customer) {
        customerService.insertCustomer(customer);
    }

    @Operation(
            description = "Update a customer",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @PutMapping("insert")
    public Object updateCustomer(@RequestBody CustomerEntity customer) {
        return customerService.updateCustomer(customer);
    }

    @Operation(
            description = "Find all customers",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @GetMapping("customers")
    public List<CustomerEntity> getCustomers() {
        return customerService.getAllCustomers();
    }

}
