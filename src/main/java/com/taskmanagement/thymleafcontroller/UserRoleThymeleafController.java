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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.UserRole;
import com.taskmanagement.exceptions.UserRoleDoesNotExistsException;
import com.taskmanagement.exceptions.UserRoleListEmptyException;
import com.taskmanagement.exceptions.UserRoleNotExistsException;
import com.taskmanagement.services.UserRoleService;

@Controller
public class UserRoleThymeleafController {
	@Autowired
	UserRoleService userRoleService;
	
	
	@GetMapping("/api1/userRoleHome")
    public String showUserRoleHomePage() {
    	return "homeUserRole";
	}
	
	
	@GetMapping("/api1/userRole")
	public String showdetailsforuserroleid()
	{
		return "userrole-form";
	}
	
	@GetMapping("/api1/userRole/userRoleid")
	public String retrieveByUserRoleId(@RequestParam("id") int id, Model model)
	{
		UserRole userRole = userRoleService.getUserRoleById(id);
		if(userRole == null)
		{
			throw new UserRoleNotExistsException("Userrole doesn't exist");
		}
		model.addAttribute("userRole", userRole);
		return "userroledetails";
	}
	
	@GetMapping("/api1/alluserroles")
	public String getAllUserRoles(Model model) throws UserRoleListEmptyException
	{
		List<UserRole> user = userRoleService.getAllUserRoles();
		model.addAttribute("AllUserRoles", user);
		model.addAttribute("userRole", user);
		return "alluserroledetails";
	}
	
	@GetMapping("/api1/deleteUserRoleForm")
    public String showDeleteUserForm() {
        return "deleteUserRoleForm";
    }

    @PostMapping("/api1/deleteUserRole")
    public String deleteCategory(@RequestParam int userId, Model model) {
        // Perform deletion operation
        boolean deleted = userRoleService.deleteUserRoleById(userId);
        // Check if deletion was successful
        if (deleted) {
            model.addAttribute("code", "DELETESUCCESS");
            model.addAttribute("message", "User Role deleted successfully");
        } else {
            model.addAttribute("code", "ENDPOINTFAILS");
            model.addAttribute("message", "User Role deletion failed because user doesn't exist");
        }
        // Return the view name for displaying the result
        return "deleteUserRoleResult";
    }
    
//    @PostMapping("/createUserRole")
//    public ResponseEntity<Map<String, String>> createUserRole(@ModelAttribute("userRole") UserRole userRole) {
//        // Ensure the 'roleName' field is not null
//        if (userRole.getRoleName() == null || userRole.getRoleName().isEmpty()) {
//            // Return an error response if 'roleName' is null or empty
//            return ResponseEntity.badRequest().body(Map.of("message", "Role name cannot be null or empty"));
//        }
//        // Call the userRoleService.createUserRole method
//        return userRoleService.createUserRole(userRole);
//    }
    
    @PostMapping("/api1/createUserRole")
  public String createUserRole(@ModelAttribute("userRole") UserRole userRole) {
      // Ensure the 'roleName' field is not null
      if (userRole.getRoleName() == null || userRole.getRoleName().isEmpty()) {
          // Return an error response if 'roleName' is null or empty
          return "Role name cannot be null or empty";
      }
      // Call the userRoleService.createUserRole method
      userRoleService.createUserRole(userRole);
      return "success";
  }
    

    @GetMapping("/api1/createUserRole")
    public String showUserRoleForm(Model model) {
        // Add a new UserRole object to the model
        model.addAttribute("userRole", new UserRole());
        // Return the name of the Thymeleaf template for the user role form
        return "postuserrole";
    }
    
  //UPDATE PROJECTID
    @GetMapping("/api1/userroleupdate")
    public String showpostUserroleUpdateForm(Model model) {
    	UserRole userRole=new UserRole ();
    	model.addAttribute("UserRole", userRole);
		return "updateUserRole";
    	
    }
 
    @GetMapping("/updates")
    public String updateUserRoleById(@RequestParam("userRoleID") int userId, @ModelAttribute("Update") UserRole updatedUserRole,Model model) {
        boolean updated = userRoleService.updateUserRoleById(userId, updatedUserRole);
        if (updated==false) {
        	throw new UserRoleNotExistsException("UserRole doesn't exist exist");	
        }
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "userRole updated successfully");
        model.addAttribute("userRole", updated);
        return "success";        
     }
 
    
    
}



