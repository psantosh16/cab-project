package com.pmo.cab.repository;

import com.pmo.cab.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
    
    Optional<Role> findByName(String name);
    
    boolean existsByName(String name);
    
    @Query("SELECT r FROM Role r JOIN FETCH r.users WHERE r.id = :id")
    Optional<Role> findByIdWithUsers(@Param("id") Short id);
    
    @Query("SELECT r FROM Role r ORDER BY r.name ASC")
    List<Role> findAllOrderByName();
}
