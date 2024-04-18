package com.taskmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.bean.User;

public interface UserService {
	
	List<User> getUsersWithMostTasks();
	
	List<User> getUsersWithCompletedTasks();
	
    List<User> getUsersByDomain(String domain);

	boolean authenticateUser(String username, String password);
	
	ResponseEntity<Map<String, String>> createUser(User user);
		
    List<User> getAllUsers();
	   
	User getUserById(int userId);
	    
	List<User> searchUsersByName(String name);
	   
	boolean updateUser(int userId, User updatedUser);
	  
	boolean deleteUser(int userId);

}
