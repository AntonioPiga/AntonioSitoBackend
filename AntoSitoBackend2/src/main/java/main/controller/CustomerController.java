package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import main.model.entity.CustomerEntity;
import main.model.dto.CustomerDto;
import main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> insertCustomer(@RequestBody CustomerDto customer) {
        return customerService.insertCustomer(customer);
    }

    @Operation(
            description = "Update a customer",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @PutMapping("update")
    public Object updateCustomer(@Parameter(name = "idCustomer", required = true, example = "1") @RequestParam Long idCustomer,
                                 @Parameter(name = "name", example = "antonio", required = true) @RequestParam String name,
                                 @Parameter(name = "email", example = "antonio@piga.it", required = true) @RequestParam String email,
                                 @Parameter(name = "message", example = "message to save here") @RequestParam String message) {
        return customerService.updateCustomer(idCustomer, name, email, message);
    }

    @Operation(
            description = "Find all customers",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @GetMapping("customers")
    public ResponseEntity<Iterable<CustomerEntity>> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("getone")
    public ResponseEntity<Object> getOneCustomer(@Parameter(name = "id", required = true) @RequestParam Long id) {

        System.out.println(id);

        return customerService.getCustomerById(id);

    }

    @Operation(
            description = "Delete customer by id",
            tags = {"customers"},
            method = "GET",
            responses = {@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)))
            })
    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteCustomer(@Parameter(name = "id", required = true) @RequestParam Long id) {

        return customerService.deleteCustomerById(id);

    }

}
