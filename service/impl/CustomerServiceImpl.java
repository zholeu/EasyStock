package com.springeasystock.easystock.service.impl;

//import com.springeasystock.easystock.Exception.ResourceNotFoundException;
import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.mapper.CustomerMapper;
import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.repo.CustomerRepository;
import com.springeasystock.easystock.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new CustomNotFoundException(customerId));
//
        return CustomerMapper.toDTO(customer);

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((customer) -> CustomerMapper.toDTO(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Integer customerId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(
                        NoSuchElementException::new
        );
        customer.setName(updatedCustomer.getName());
        customer.setSurname(updatedCustomer.getSurname());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAddress(updatedCustomer.getAddress());
        Customer updatedCustomerObj =  customerRepository.save(customer);
        return CustomerMapper.toDTO(updatedCustomerObj);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(NoSuchElementException::new
//                () -> new ResourceNotFoundException("Customer with such id doesn't exist" + customerId)

        );
        customerRepository.deleteById(customer.getId());
    }
}
