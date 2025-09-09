package com.pmo.cab.service;

import com.pmo.cab.dto.LoginRequest;
import com.pmo.cab.dto.LoginResponse;
import com.pmo.cab.dto.RoleDto;
import com.pmo.cab.dto.UserDto;
import com.pmo.cab.entity.User;
import com.pmo.cab.exception.UnauthorizedException;
import com.pmo.cab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password("") // We'll handle password separately
                .authorities(user.getRoles().stream()
                        .map(role -> "ROLE_" + role.getName().toUpperCase())
                        .toArray(String[]::new))
                .build();
    }
    
    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            
            User user = userRepository.findByUsernameWithRoles(request.getUsername())
                    .orElseThrow(() -> new UnauthorizedException("User not found"));
            
            if (!user.getIsActive()) {
                throw new UnauthorizedException("User account is deactivated");
            }
            
            String token = jwtService.generateToken(request.getUsername());
            
            Set<RoleDto> roles = user.getRoles().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toSet());
            
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUuid(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getDisplayName(),
                    user.getIsActive(),
                    user.getCreatedAt(),
                    user.getUpdatedAt(),
                    roles
            );
            
            return new LoginResponse(token, userDto, roles);
            
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid username or password");
        }
    }
}
