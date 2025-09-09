package com.pmo.cab.dto;

import java.util.Set;

public class LoginResponse {
    
    private String token;
    private String type = "Bearer";
    private UserDto user;
    private Set<RoleDto> roles;
    
    // Constructors
    public LoginResponse() {}
    
    public LoginResponse(String token, UserDto user, Set<RoleDto> roles) {
        this.token = token;
        this.user = user;
        this.roles = roles;
    }
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public UserDto getUser() {
        return user;
    }
    
    public void setUser(UserDto user) {
        this.user = user;
    }
    
    public Set<RoleDto> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
