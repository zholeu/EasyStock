package com.springeasystock.easystock.controller;

import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.OrderList;
import com.springeasystock.easystock.repo.EmployeeRepository;
import com.springeasystock.easystock.repo.RoleRepository;
import com.springeasystock.easystock.service.CustomerService;
import com.springeasystock.easystock.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedCustomer = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<EmployeeDTO> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }


    @PutMapping("/{employeeId}/role/{roleId}")
    public Employee assignItemToOrderList(
            @PathVariable Integer employeeId,
            @PathVariable Integer roleId
    ){
        return employeeService.assignItemToOrderList(employeeId, roleId);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Integer employeeId){
        EmployeeDTO savedEmployee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(savedEmployee);

    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id")Integer employeeId,
                                                      @RequestBody EmployeeDTO updatedEmployee){
        EmployeeDTO employeeDTO = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Deleted Successfully!");

    }


}
