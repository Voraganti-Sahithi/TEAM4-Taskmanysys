package com.taskmanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.taskmanagement.exceptions.UserAlreadyExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistsException;
import com.taskmanagement.exceptions.UserListEmptyException;
import com.taskmanagement.exceptions.UserNotExistsException;
import com.taskmanagement.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
    public UserController() {
		
	}

	public UserController(UserService userservice) {
		this.userService = userservice;
	}
	
	//POSTING USER DATA
    @PostMapping("/post")
	 public ResponseEntity<Map<String, String>> createUser(@RequestBody User user)
	 {
		return userService.createUser(user);
	 }
    
    
    //GETTING ALL USERS DATA
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty())
        	throw new UserListEmptyException("User list is empty");
        return ResponseEntity.ok(users);
    }
     
    
    
   //GETTING USER DATA BY USER ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User users = userService.getUserById(userId);
        if(users == null)
        	throw new UserNotExistsException("User doesn't exist exist");
        return ResponseEntity.ok(users);
        	
    }
    
    
    // GET DATA FROM USER TABLE BY USING EMAIL-DOMAIN
 	@GetMapping("/email-domain/{domain}")
     public List<User> getUsersByDomain(@PathVariable String domain) {
 		if(userService.getUsersByDomain(domain).isEmpty()) {
 		throw new UserDoesNotExistsException("User doesn't exists");
 	}
 	
         return userService.getUsersByDomain(domain);
     }
 	
 	
 	//GETTING USER DATA BY USER NAME
    @GetMapping("/search/{name}")
    public ResponseEntity<List<User>> searchUsersByName(@PathVariable String name) {
        List<User> users = userService.searchUsersByName(name);
        if(users.isEmpty())
        	throw new UserNotExistsException("User doesn't exist exist");
        return ResponseEntity.ok(users);
    }
    
    
	//GETTING USERS BASED ON MOST-TASKS
	@GetMapping("/most-tasks")
    public List<User> getUsersWithMostTasks() {
		if(userService.getUsersWithMostTasks().isEmpty())
			throw new UserListEmptyException("User list is empty");
        return userService.getUsersWithMostTasks();
    }
	
	
	//AUTHENTICATE USER DETAILS BY USING USERNAME AND PASSWORD
	@GetMapping("/authenticate/{userName}/{password}")
    public ResponseEntity<Map<String,String>> authenticateUser(@PathVariable String userName,@PathVariable String password) {
        if (userService.authenticateUser(userName, password) ){
            Map<String ,String> response=new HashMap<>();
            response.put(  "code","AUTHSUCCESS");
            response.put("message","User is valid");
            return ResponseEntity.ok(response);
        } else {
            throw new UserAlreadyExistsException("Invalid credentials");
        }
	}
    
    
	//GETTING USERS BASED ON COMPLETED-TASKS
	@GetMapping("/completed-tasks")
    public List<User> getUsersWithCompletedTasks() {
		if(userService.getUsersWithCompletedTasks().isEmpty())
			throw new UserListEmptyException("User list is empty");
        return userService.getUsersWithCompletedTasks();
    }
	

    //UPDATING USER DATA BY USER ID
    @PutMapping("/update/{userId}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        boolean updated = userService.updateUser(userId, updatedUser);
        if (updated==false) {
            throw new UserDoesNotExistsException("User doesn't exist exist");
        } 
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "User updated successfully");
        return ResponseEntity.ok(response);
    }
    
    
	//DELETING USER BY USER ID
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted== false) {
        	throw new UserDoesNotExistExistsException("User doesn't exist exist");
        
           
        } 
            Map<String, String> response = new HashMap<>();
            response.put("code", "DELETESUCCESS");
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        }
    }


