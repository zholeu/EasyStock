package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.mapper.CustomerMapper;
import com.springeasystock.easystock.mapper.EmployeeMapper;
import com.springeasystock.easystock.model.*;
import com.springeasystock.easystock.repo.EmployeeRepository;
import com.springeasystock.easystock.repo.RoleRepository;
import com.springeasystock.easystock.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.toDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public Employee assignItemToOrderList(Integer employeeId, Integer roleId) {
        Set<Role> roles = null;
        Employee employee = employeeRepository.findById(employeeId).get();
        Role role = roleRepository.findById(roleId).get();
        roles = employee.getRoles();
        roles.add(role);
        employee.setRoles(roles);
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        return EmployeeMapper.toDTO(employee);

    }

    @Override
    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        employee.setUsername(updatedEmployee.getUsername());
        employee.setRole(updatedEmployee.getRole());
        employee.setCreatedAt(updatedEmployee.getCreatedAt());
        employee.setZone(updatedEmployee.getZoneId());
        Employee updatedEmployeeObj =  employeeRepository.save(employee);
        return EmployeeMapper.toDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomNotFoundException(employeeId));
        employeeRepository.deleteById(employee.getId());
    }
}
