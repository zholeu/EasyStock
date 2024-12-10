package com.springeasystock.easystock.record;

import com.springeasystock.easystock.controller.validator.ValidEmployee;
import com.springeasystock.easystock.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private Long id;
    @NotBlank(message = "Name of role is mandatory")
    @Size(min = 2, max = 50, message = "Name of role must be between 2 and 50 characters")
    private String name;
    @ValidEmployee
    private Set<Employee> employees;
}
