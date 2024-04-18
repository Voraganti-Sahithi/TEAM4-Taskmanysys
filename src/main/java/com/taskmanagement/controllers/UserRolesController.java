package com.taskmanagement.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.bean.Project;
import com.taskmanagement.bean.UserRole;
import com.taskmanagement.bean.UserRoles;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.exceptions.UserRolesAlreadyExistException;
import com.taskmanagement.exceptions.UserRolesDoesNotExistsException;
import com.taskmanagement.exceptions.UserRolesListEmptyException;
import com.taskmanagement.exceptions.UserRolesNotExistsException;
import com.taskmanagement.services.UserRolesService;



@RestController
@RequestMapping("/api/userroles")

public class UserRolesController {
	@Autowired
	private UserRolesService userRolesService;
	
	public UserRolesController() {
		super();
	}
	public UserRolesController(UserRolesService userRolesService) {
		super();
		this.userRolesService = userRolesService;
	}
	
	
	//POST ASSIGN
	@PostMapping("/assign")
    public ResponseEntity<Map<String, String>> assignUserRole(@RequestBody UserRoles userRoles) {
        UserRoles assignedUserRole = userRolesService.assignUserRole(userRoles);
        if(assignedUserRole==null) {
        	throw new UserRolesAlreadyExistException("UserRole already exist");
        }
        Map<String, String> response = new HashMap<>();
        response.put("code", "POSTSUCCESS");
        response.put("message", "Userrole added successfully");
        return ResponseEntity.ok(response);
    }
	
	
	//GET ALL
	@GetMapping("/all")
    public List<UserRoles> getAllUserRoles() {
		List<UserRoles> userRoles = userRolesService.getAllUserRoles();
		if(userRoles == null || userRoles.isEmpty()) {
			throw new UserRolesListEmptyException("UserRole list is empty");
		}
        return userRoles;
    }
	
	
	//GET USERID  
	@GetMapping("/user/{userId}")
	public List<UserRoles> getUserRolesByUserId(@PathVariable int userId) {
	    List<UserRoles> userRoles = userRolesService.getUserRolesByUserId(userId);
	    if(userRoles.isEmpty()) {
	        throw new UserRolesListEmptyException("User Roles doesn't exist");	
	    }
	    return userRoles;
	}
 
	
	//DELETE USERROLEID
	@DeleteMapping("/revoke/{userRoleId}/{userId}")
    public ResponseEntity<Map<String, String>> deleteUserRole(@PathVariable int userId, @PathVariable int userRoleId) {
		boolean b =  userRolesService.deleteUserRoles(userId,userRoleId);
		if(b==false)
			throw new UserRolesDoesNotExistsException("UserRole doesn't exist");
		Map<String, String> response = new HashMap<>();
        response.put("code", "DELETESUCCESS");
        response.put("message", "UserRoles deleted successfully");
        return ResponseEntity.ok(response);
    }
	
}
