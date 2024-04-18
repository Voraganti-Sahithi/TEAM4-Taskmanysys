package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.User;
import com.taskmanagement.exceptions.UserAlreadyExistsException;
import com.taskmanagement.exceptions.UserDoesNotExistsException;
import com.taskmanagement.exceptions.UserListEmptyException;
import com.taskmanagement.exceptions.UserNotExistsException;
import com.taskmanagement.services.UserService;

@Controller
@RequestMapping("/api1")
public class UserThymeleafController {
	@Autowired
	UserService userservice;
	
	public UserThymeleafController() {
		
	}

	public UserThymeleafController(UserService userservice) {
		this.userservice = userservice;
	}
	
	//Home page for all Controllers
	 @GetMapping("/homepage")
	    public String showHomepage() {
	        return "home";
	    }
	 
	@GetMapping("/userHome")
    public String showUserHomePage() {
    	return "homeUser";
	}
	
	
	@GetMapping("/user/email")
	public String User() {
		return "Get-User";
	}
		
	@GetMapping("/user/email-domain")
	public String getUsersByDomain(@RequestParam ("email") String domain, Model model){
			List<User> user= userservice.getUsersByDomain(domain);
			if(userservice.getUsersByDomain(domain).isEmpty()) {
				
				throw new UserDoesNotExistsException("User doesn't exists");
		}
			model.addAttribute("userdetails",user);
			model.addAttribute("user",user);
			return "User-Details";
		}
	
		@GetMapping("/user/authentication")
		public String Usersuthenticstion() {
			return "User-authenticationForm";
		}
		
		
		

		@GetMapping("/user/authentication/authenticate")
	    public String authenticateUser(@RequestParam ("userName") String userName,@RequestParam ("password") String password , Model model) {
	        if (userservice.authenticateUser(userName, password) ){
	            model.addAttribute("user",userName);
	        	model.addAttribute("password",password);
	            return "Authentication-Successfull";
	        } else {
	            throw new UserAlreadyExistsException("Invalid credentials");
	        }
	    }
		
		//GETTING USERS BASED ON MOST-TASKS
		@GetMapping("/users1/most-tasks")
		public String getUsersWithMostTasks(Model model) {
			
			List<User> users = userservice.getUsersWithMostTasks();
			try {
	            if (users.isEmpty()) {
	            	throw new UserListEmptyException("No such user exists who assigned with most tasks");
	            }
	            
	        } catch (UserListEmptyException e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "error"; 
	        }
			model.addAttribute("users", users);
			return "most-tasks";
		}
			
		
		//GETTING USERS BASED ON COMPLETED-TASKS
		@GetMapping("/users1/completed-tasks")
		public String getUsersWithCompletedTasks(Model model) {
			List<User> users=userservice.getUsersWithCompletedTasks();
			
			try {
	            if (users.isEmpty()) {
	            	throw new UserListEmptyException("No such user exists whose tasks are completed");
	            }
	            
	        } catch (UserListEmptyException e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "error"; 
	        }
			model.addAttribute("users", users);
		    return "completed-tasks";
		}
		
		
		@GetMapping("/user")
		public String showdetailsforuserid()
		{
			return "user-form";
		}
		
		@GetMapping("/user/userid")
		public String retrieveByUserId(@RequestParam("id") int id, Model model)
		{
			User user = userservice.getUserById(id);
			if(user == null)
			{
				throw new UserNotExistsException("User doesn't exist");
			}
			model.addAttribute("user", user);
			return "userdetails";
		}
		
		@GetMapping("/allusers")
		public String getAllUsers(Model model) throws UserListEmptyException
		{
			List<User> user = userservice.getAllUsers();
			model.addAttribute("AllUsers", user);
			model.addAttribute("user", user);
			return "alluserdetails";
		}
		
		@GetMapping("/search/{name}")
	    public String searchUsersByName(@PathVariable String name, Model model) {
	        List<User> users = userservice.searchUsersByName(name);
	        if (users.isEmpty()) {
	            throw new UserNotExistsException("User doesn't exist");
	        }
	        model.addAttribute("users", users);
	        return "userResults"; 
	    }
		
		@GetMapping("/deleteUserForm")
	    public String showDeleteUserForm() {
	        return "deleteform";
	    }

	    @PostMapping("/deleteUser")
	    public String deleteCategory(@RequestParam int userId, Model model) {
	        // Perform deletion operation
	        boolean deleted = userservice.deleteUser(userId);
	        // Check if deletion was successful
	        if (deleted) {
	            model.addAttribute("code", "DELETESUCCESS");
	            model.addAttribute("message", "User deleted successfully");
	        } else {
	            model.addAttribute("code", "ENDPOINTFAILS");
	            model.addAttribute("message", "User deletion failed because user doesn't exist");
	        }
	        // Return the view name for displaying the result
	        return "deleteUserResult";
	    }
	    
	    
//	    @PostMapping("/createU")
//	    public ResponseEntity<Map<String, String>> createUser(@ModelAttribute("user") User user) {
//	        // Ensure the 'Username' field is not null
//	        if (user.getUsername() == null || user.getUsername().isEmpty()) {
//	            // Return an error response if 'Username' is null or empty
//	            return ResponseEntity.badRequest().body(Map.of("message", "Username cannot be null or empty"));
//	        }
//	        // Call the userService.createUser method
//	        return userservice.createUser(user);
//	        
//	    }
	    
	    @PostMapping("/createU")
	    public String createUser(@ModelAttribute("user") User user) {
	    	
	        // Ensure the 'Username' field is not null
	        if (user.getUsername() == null || user.getUsername().isEmpty()) {
	            // Return an error response if 'Username' is null or empty
	            return "Username cannot be null or empty";
	        }
	        // Call the userService.createUser method
	        
	        userservice.createUser(user);
	        return "success";
	        
	    }
	    
	    

	    @GetMapping("/createUser")
	    public String showUserForm(Model model) throws UserAlreadyExistsException {
	        // Add a new User object to the model
	        model.addAttribute("user", new User());
	        // Return the name of the Thymeleaf template
	        return "postuser";
	    }
}

