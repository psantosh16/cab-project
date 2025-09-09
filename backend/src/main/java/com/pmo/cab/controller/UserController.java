package com.pmo.cab.controller;

import com.pmo.cab.dto.*;
import com.pmo.cab.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserDto user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", user));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }
    
    @GetMapping("/username/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<UserDto>> getUserByUsername(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }
    
    @GetMapping("/uuid/{uuid}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<UserDto>> getUserByUuid(@PathVariable String uuid) {
        UserDto user = userService.getUserByUuid(uuid);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }
    
    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getActiveUsers() {
        List<UserDto> users = userService.getActiveUsers();
        return ResponseEntity.ok(ApiResponse.success("Active users retrieved successfully", users));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id, 
                                                          @Valid @RequestBody UpdateUserRequest request) {
        UserDto user = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", user));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }
    
    @PostMapping("/{userId}/roles/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> assignRoleToUser(@PathVariable Long userId, 
                                                             @PathVariable Short roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok(ApiResponse.success("Role assigned to user successfully"));
    }
    
    @DeleteMapping("/{userId}/roles/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> removeRoleFromUser(@PathVariable Long userId, 
                                                               @PathVariable Short roleId) {
        userService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.ok(ApiResponse.success("Role removed from user successfully"));
    }
    
    @GetMapping("/{userId}/roles")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<List<RoleDto>>> getUserRoles(@PathVariable Long userId) {
        List<RoleDto> roles = userService.getUserRoles(userId);
        return ResponseEntity.ok(ApiResponse.success("User roles retrieved successfully", roles));
    }
}
