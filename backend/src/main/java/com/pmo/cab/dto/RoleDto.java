package com.pmo.cab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleDto {
    
    private Short id;
    
    @NotBlank(message = "Role name is required")
    @Size(min = 2, max = 100, message = "Role name must be between 2 and 100 characters")
    private String name;
    
    // Constructors
    public RoleDto() {}
    
    public RoleDto(Short id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    public Short getId() {
        return id;
    }
    
    public void setId(Short id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
