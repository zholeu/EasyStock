package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.model.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        return  new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    public static Customer toEntity(CustomerDTO dto) {
        return  new Customer(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getAddress()
        );
    }
}

