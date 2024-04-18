package com.taskmanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.bean.User;
import com.taskmanagement.bean.UserRole;
import com.taskmanagement.exceptions.UserDoesNotExistsException;
import com.taskmanagement.exceptions.UserListEmptyException;
import com.taskmanagement.exceptions.UserNotExistsException;
import com.taskmanagement.exceptions.UserRoleDoesNotExistsException;
import com.taskmanagement.exceptions.UserRoleNotExistsExistsException;
import com.taskmanagement.services.UserRoleService;

@RestController
@RequestMapping("/api/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;
    
    //POSTING USERROLE DATA
    @PostMapping("/post")
	 public ResponseEntity<Map<String, String>> createUserRole(@RequestBody UserRole userrole)
	 {
		return userRoleService.createUserRole(userrole);
	 }
     
    //GETTING ALL USERROLES DATA
    @GetMapping("/all")
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        if(userRoles.isEmpty())
        	throw new UserListEmptyException("Userrole list is empty");
        return ResponseEntity.ok(userRoles);
    }    
    
    //GETTING USERROLE DATA BY USERROLE ID
    @GetMapping("/{userRoleId}")
    public ResponseEntity<UserRole> getUserRoleById(@PathVariable int userRoleId) {
        UserRole userRole = userRoleService.getUserRoleById(userRoleId);
        	if(userRole == null) 
            	throw new UserNotExistsException("User doesn't exist ");
            return ResponseEntity.ok(userRole);
            	
     }
    
   
    //UPDATING USERROLE BY USERROLEID
    @PutMapping("/update/{userId}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable int userId, @RequestBody UserRole updatedUser) {
        boolean updated = userRoleService.updateUserRoleById(userId, updatedUser);
        if (updated==false) {
        	throw new UserRoleDoesNotExistsException("UserRole doesn't exist exist");
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("code", "UPDATESUCCESS");
            response.put("message", "UseroleS updated successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    
    //DELETING BY USERROLE ID
    @DeleteMapping("/delete/{userRoleId}")
    public ResponseEntity<Map<String, String>> deleteUserRoleById(@PathVariable int userRoleId) {
        boolean deleted = userRoleService.deleteUserRoleById(userRoleId);
        if (deleted) {
            Map<String, String> response = new HashMap<>();
            response.put("code", "DELETESUCCESS");
            response.put("message", "UserRoleid deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            throw new UserRoleNotExistsExistsException("UserRoleid doesn't exist");
        }
    }
}
