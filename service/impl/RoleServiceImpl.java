package com.springeasystock.easystock.service.impl;

import com.springeasystock.easystock.Exception.CustomNotFoundException;
import com.springeasystock.easystock.dto.RoleDTO;
import com.springeasystock.easystock.mapper.RoleMapper;
import com.springeasystock.easystock.model.Role;
import com.springeasystock.easystock.repo.RoleRepository;
import com.springeasystock.easystock.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;


    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = RoleMapper.toEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.toDTO(savedRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map((role) -> RoleMapper.toDTO(role))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        return RoleMapper.toDTO(role);

    }

    @Override
    public RoleDTO updateRole(Integer roleId, RoleDTO updatedRole) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        role.setName(updatedRole.getName());
        Role updatedObj =  roleRepository.save(role);
        return RoleMapper.toDTO(updatedObj);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException(roleId));
        roleRepository.deleteById(role.getId());
    }
}
