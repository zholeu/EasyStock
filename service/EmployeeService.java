package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();


    Employee assignItemToOrderList(Integer employeeId, Integer roleId);

    EmployeeDTO getEmployeeById(Integer employeeId);

    EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO updatedEmployee);


    void deleteEmployee(Integer employeeId);
}
