package com.pmo.cab.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    
    private Long userId;
    private Short roleId;
    
    // Constructors
    public UserRoleId() {}
    
    public UserRoleId(Long userId, Short roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    
    // Getters and Setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Short getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }
    
    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
