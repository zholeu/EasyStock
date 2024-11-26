package com.springeasystock.easystock.dto;

import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.model.Zone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String username;
    private String role;
    private Timestamp createdAt  = new Timestamp(System.currentTimeMillis());
    private Zone zoneId;
    private Set<Role> roleIds; // Can be a list of role IDs
}
