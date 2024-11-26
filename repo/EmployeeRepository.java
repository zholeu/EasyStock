package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    List<Employee> findByName(String name);
}
