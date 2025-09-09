package com.pmo.cab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @Column(name = "id")
    private Short id;
    
    @NotBlank(message = "Role name is required")
    @Size(min = 2, max = 100, message = "Role name must be between 2 and 100 characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
    
    // Constructors
    public Role() {}
    
    public Role(Short id, String name) {
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
    
    public Set<User> getUsers() {
        return users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    // Helper methods
    public void addUser(User user) {
        this.users.add(user);
        user.getRoles().add(this);
    }
    
    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
