package com.taskmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.UserRoles;

import jakarta.transaction.Transactional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer>
{
	List<UserRoles> findByUserUserID(Integer userId);
	
    @Modifying
	@Query("DELETE FROM UserRoles ur WHERE ur.user.userID = :userId AND ur.userRole.userRoleID = :userRoleId")
    void deleteUserRoleByUserIdAndRoleId(@Param("userId") int userId, @Param("userRoleId") int userRoleId);
    
    @Modifying
	@Query("DELETE from UserRoles ur where ur.user.userID=:userId")
	void deleteByUserId(int userId);
    
    @Transactional
	@Modifying
	@Query("DELETE FROM UserRoles ur WHERE ur.userRole.userRoleID=:userRoleId")
	void deleteByUserRoleId(int userRoleId);
    
    boolean existsByUserRoleUserRoleIDAndUserUserID(int userRoleId, int userId);
}
