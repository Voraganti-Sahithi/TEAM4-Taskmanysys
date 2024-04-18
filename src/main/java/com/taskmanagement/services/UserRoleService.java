package com.taskmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.taskmanagement.bean.User;
import com.taskmanagement.bean.UserRole;

public interface UserRoleService {
	
		ResponseEntity<Map<String, String>> createUserRole(@RequestBody UserRole userrole);
	     
		List<UserRole> getAllUserRoles();
		
	    UserRole getUserRoleById(int userRoleId);
	    
	    boolean updateUserRoleById(int userId, UserRole updatedUserRole);
	 
		boolean deleteUserRoleById(int userRoleId);
	}


