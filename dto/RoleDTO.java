package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.Employee;
import com.springeasystock.easystock.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Integer id;
    private String name;
    private Set<Employee> employees;
}
