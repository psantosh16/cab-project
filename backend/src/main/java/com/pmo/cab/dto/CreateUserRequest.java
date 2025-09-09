package com.pmo.cab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    private String username;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @Size(max = 255, message = "Display name must not exceed 255 characters")
    private String displayName;
    
    // Constructors
    public CreateUserRequest() {}
    
    public CreateUserRequest(String username, String email, String displayName) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
    }
    
    // Getters and Setters
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
}
