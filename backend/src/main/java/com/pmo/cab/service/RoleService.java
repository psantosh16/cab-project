package com.pmo.cab.service;

import com.pmo.cab.dto.CreateRoleRequest;
import com.pmo.cab.dto.RoleDto;
import com.pmo.cab.entity.Role;
import com.pmo.cab.exception.ResourceAlreadyExistsException;
import com.pmo.cab.exception.ResourceNotFoundException;
import com.pmo.cab.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public RoleDto createRole(CreateRoleRequest request) {
        // Check if role ID already exists
        if (roleRepository.existsById(request.getId())) {
            throw new ResourceAlreadyExistsException("Role", "id", request.getId());
        }
        
        // Check if role name already exists
        if (roleRepository.existsByName(request.getName())) {
            throw new ResourceAlreadyExistsException("Role", "name", request.getName());
        }
        
        Role role = new Role();
        role.setId(request.getId());
        role.setName(request.getName());
        
        Role savedRole = roleRepository.save(role);
        return convertToDto(savedRole);
    }
    
    @Transactional(readOnly = true)
    public RoleDto getRoleById(Short id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        return convertToDto(role);
    }
    
    @Transactional(readOnly = true)
    public RoleDto getRoleByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", name));
        return convertToDto(role);
    }
    
    @Transactional(readOnly = true)
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAllOrderByName().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public void deleteRole(Short id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        
        roleRepository.delete(role);
    }
    
    private RoleDto convertToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
