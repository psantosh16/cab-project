package com.pmo.cab.controller;

import com.pmo.cab.dto.ApiResponse;
import com.pmo.cab.dto.CreateRoleRequest;
import com.pmo.cab.dto.RoleDto;
import com.pmo.cab.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@Valid @RequestBody CreateRoleRequest request) {
        RoleDto role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Role created successfully", role));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Short id) {
        RoleDto role = roleService.getRoleById(id);
        return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
    }
    
    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleByName(@PathVariable String name) {
        RoleDto role = roleService.getRoleByName(name);
        return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Short id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(ApiResponse.success("Role deleted successfully"));
    }
}
