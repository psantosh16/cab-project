package com.pmo.cab.service;

import com.pmo.cab.entity.Role;
import com.pmo.cab.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DataInitializationService implements CommandLineRunner {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }
    
    private void initializeRoles() {
        // Create default roles if they don't exist
        if (!roleRepository.existsById((short) 1)) {
            Role adminRole = new Role((short) 1, "ADMIN");
            roleRepository.save(adminRole);
        }
        
        if (!roleRepository.existsById((short) 2)) {
            Role userRole = new Role((short) 2, "USER");
            roleRepository.save(userRole);
        }
        
        if (!roleRepository.existsById((short) 3)) {
            Role managerRole = new Role((short) 3, "MANAGER");
            roleRepository.save(managerRole);
        }
    }
}
