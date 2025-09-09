package com.pmo.cab.service;

import com.pmo.cab.dto.*;
import com.pmo.cab.entity.Role;
import com.pmo.cab.entity.User;
import com.pmo.cab.exception.ResourceAlreadyExistsException;
import com.pmo.cab.exception.ResourceNotFoundException;
import com.pmo.cab.repository.RoleRepository;
import com.pmo.cab.repository.UserRepository;
import com.pmo.cab.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public UserDto createUser(CreateUserRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistsException("User", "username", request.getUsername());
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("User", "email", request.getEmail());
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setDisplayName(request.getDisplayName());
        user.setIsActive(true);
        
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return convertToDto(user);
    }
    
    @Transactional(readOnly = true)
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return convertToDto(user);
    }
    
    @Transactional(readOnly = true)
    public UserDto getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "uuid", uuid));
        return convertToDto(user);
    }
    
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<UserDto> getActiveUsers() {
        return userRepository.findAllActiveUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public UserDto updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        
        // Check if username is being changed and if it already exists
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new ResourceAlreadyExistsException("User", "username", request.getUsername());
            }
            user.setUsername(request.getUsername());
        }
        
        if (request.getDisplayName() != null) {
            user.setDisplayName(request.getDisplayName());
        }
        
        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }
        
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }
    
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        
        // Soft delete by setting isActive to false
        user.setIsActive(false);
        userRepository.save(user);
    }
    
    public void assignRoleToUser(Long userId, Short roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        
        if (!userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            user.addRole(role);
            userRepository.save(user);
        }
    }
    
    public void removeRoleFromUser(Long userId, Short roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        
        user.removeRole(role);
        userRepository.save(user);
    }
    
    @Transactional(readOnly = true)
    public List<RoleDto> getUserRoles(Long userId) {
        User user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        return user.getRoles().stream()
                .map(this::convertRoleToDto)
                .collect(Collectors.toList());
    }
    
    private UserDto convertToDto(User user) {
        Set<RoleDto> roleDtos = user.getRoles().stream()
                .map(this::convertRoleToDto)
                .collect(Collectors.toSet());
        
        return new UserDto(
                user.getId(),
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                user.getDisplayName(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                roleDtos
        );
    }
    
    private RoleDto convertRoleToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
