package com.springeasystock.easystock.repo;

import com.springeasystock.easystock.model.Customer;
import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    List<Employee> findByRole(String name);

}
