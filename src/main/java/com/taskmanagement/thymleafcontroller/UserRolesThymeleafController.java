package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.bean.UserRoles;
import com.taskmanagement.exceptions.UserRoleListEmptyException;
import com.taskmanagement.exceptions.UserRolesAlreadyExistException;
import com.taskmanagement.exceptions.UserRolesDoesNotExistsException;
import com.taskmanagement.exceptions.UserRolesListEmptyException;
import com.taskmanagement.services.UserRolesService;

@Controller

public class UserRolesThymeleafController {
	@Autowired
	private UserRolesService userRolesService;
	
	
	public UserRolesThymeleafController(UserRolesService userRolesService) {
		super();
		this.userRolesService = userRolesService;
	}
	
//---------------------------------------------------------------------------------
//--------------------------------------------------------------------
	
	@GetMapping("/api1/userRolesHome")
    public String showUserRolesHomePage() {
    	return "homeUserRoles";
	}
	
	//POST ASSIGN
		@PostMapping("/assign")
	    public String assignUserRole(Model model,@ModelAttribute("userRoles") UserRoles userRoles) {
	        UserRoles assignedUserRole = userRolesService.assignUserRole(userRoles);
	        if(assignedUserRole==null) {
	        	throw new UserRolesAlreadyExistException("UserRole already exist");
	        }
	        model.addAttribute("userroles", assignedUserRole);
	        return "AssignDetails";
	    }
		@GetMapping("/api1/addpost")
	    public String showaddhForm(Model model) {
			UserRoles user=new UserRoles();
			model.addAttribute("userroles",user);
	        return "AssignForm";
	    }
	
	

//-----------------------------------------------------------------------------
//------------------------------------------------------------------------------	
		
		
	@GetMapping("/api1/all")
	public String getAllUserRoles(Model model) throws UserRoleListEmptyException
	{
		List<UserRoles> user = userRolesService.getAllUserRoles();
		model.addAttribute("userRole", user);
		return "alluserrolesform";
	}
	
	
//-------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------
	
	@GetMapping("/api1/userroles")
    public String showuserid() {
   	 return "UserIdForm";
    }
	//GET USERID  
		@GetMapping("/user/userId")
		public String getUserRolesByUserId(@RequestParam("userId") int userId,Model model) {
		    List<UserRoles> userRoles = userRolesService.getUserRolesByUserId(userId);
		    if(userRoles.isEmpty()) {
		        throw new UserRolesListEmptyException("User Roles doesn't exist");	
		    }
		    model.addAttribute("userRoles", userRoles);
		    return "Userroles-Details";
		}
		
//-----------------------------------------------------------------------------------------------------------		
//----------------------------------------------------------------------------------------------------------	
		
		@GetMapping("/api1/revoke")
	    public String showdelete(Model model) {
	    	return "RevokeForm";
	    }
	    
		//DELETE USERROLEID
		@GetMapping("/revokes")
	    public String deleteUserRoles(@RequestParam("userID") int userId, @RequestParam("userRoleID") int userRoleId,Model model) {
			boolean b =  userRolesService.deleteUserRoles(userId,userRoleId);
			if(b==false)
				return "error";
			else {
	        model.addAttribute("revoke", b);
	        return "RevokeDetails";
	    }}
		
		
//		----------------------------
		@GetMapping("api1/home")
	    public String Home(Model model) {
	    	return "Home";
	    }
				
}
