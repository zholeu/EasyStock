package com.springeasystock.easystock.mapper;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;
import com.springeasystock.easystock.repo.RoleRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

//@Component

public class EmployeeMapper {

//    @Autowired
//    private RoleRepository roleRepository;
//
//    public static EmployeeDTO toDTO(Employee employee) {
//        return  new EmployeeDTO(
//                employee.getId(),
//                employee.getUsername(),
//                employee.getRole(),
//                employee.getCreatedAt(),
//                employee.getZone(),
//                employee.getRoles()
//
//        );
//    }
//
//    public Employee toEntity(EmployeeDTO dto) {
//        // Fetch roles by IDs and set them to the employee
//
//
//        return  new Employee(
//                dto.getId(),
//                dto.getUsername(),
//                dto.getRole(),
//                dto.getCreatedAt(),
//                dto.getZoneId(),
//                dto.getRoleIds()
//
//        );
//    }
    public static EmployeeDTO toDTO(Employee employee) {
        return  new EmployeeDTO(
                employee.getId(),
                employee.getUsername(),
                employee.getRole(),
                employee.getCreatedAt(),
                employee.getZone(),
                employee.getRoles()
        );
    }

    public static Employee toEntity(EmployeeDTO dto) {
        return  new Employee(
                dto.getId(),
                dto.getUsername(),
                dto.getRole(),
                dto.getCreatedAt(),
                dto.getZoneId(),
                dto.getRoleIds()
        );
    }

}

