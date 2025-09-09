package com.pmo.cab.dto;

import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    private String username;
    
    @Size(max = 255, message = "Display name must not exceed 255 characters")
    private String displayName;
    
    private Boolean isActive;
    
    // Constructors
    public UpdateUserRequest() {}
    
    public UpdateUserRequest(String username, String displayName, Boolean isActive) {
        this.username = username;
        this.displayName = displayName;
        this.isActive = isActive;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
}
