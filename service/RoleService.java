package com.springeasystock.easystock.service;

import com.springeasystock.easystock.dto.EmployeeDTO;
import com.springeasystock.easystock.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();

    RoleDTO getRoleById(Integer roleId);

    RoleDTO updateRole(Integer roleId, RoleDTO updatedItem);

    void deleteRole(Integer roleId);
}
