package com.pmo.cab.repository;

import com.pmo.cab.entity.UserRole;
import com.pmo.cab.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    
    @Query("SELECT ur FROM UserRole ur WHERE ur.userId = :userId")
    List<UserRole> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ur FROM UserRole ur WHERE ur.roleId = :roleId")
    List<UserRole> findByRoleId(@Param("roleId") Short roleId);
    
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.userId = :userId AND ur.roleId = :roleId")
    void deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Short roleId);
    
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") Short roleId);
    
    boolean existsByUserIdAndRoleId(Long userId, Short roleId);
}
