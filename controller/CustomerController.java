package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Integer customerId){
        CustomerDTO savedCustomer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(savedCustomer);
//        return new ResponseEntity<CustomerDTO>(savedCustomer, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id")Integer customerId,
                                                      @RequestBody CustomerDTO updatedCustomer){
        CustomerDTO customerDTO = customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(customerDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id")Integer customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Deleted Successfully!");

    }
}
