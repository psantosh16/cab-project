package com.pmo.cab.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {
    
    @Id
    @Column(name = "user_id")
    private Long userId;
    
    @Id
    @Column(name = "role_id")
    private Short roleId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
    
    // Constructors
    public UserRole() {}
    
    public UserRole(Long userId, Short roleId) {
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}
