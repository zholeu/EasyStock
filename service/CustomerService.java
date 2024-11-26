package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import java.util.List;
public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer customerId);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Integer customerId, CustomerDTO updatedCustomer);

    void deleteCustomer(Integer customerId);
}
