package com.pmo.cab.dto;

import com.pmo.cab.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {
    
    private Long id;
    private String uuid;
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    private String username;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @Size(max = 255, message = "Display name must not exceed 255 characters")
    private String displayName;
    
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<RoleDto> roles;
    
    // Constructors
    public UserDto() {}
    
    public UserDto(Long id, String uuid, String username, String email, String displayName, 
                   Boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, Set<RoleDto> roles) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Set<RoleDto> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
