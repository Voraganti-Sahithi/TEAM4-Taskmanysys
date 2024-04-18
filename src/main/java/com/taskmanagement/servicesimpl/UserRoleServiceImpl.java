package com.taskmanagement.servicesimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.User;
import com.taskmanagement.bean.UserRole;
import com.taskmanagement.exceptions.UserRoleAlreadyExistsException;
import com.taskmanagement.repositories.UserRoleRepository;
import com.taskmanagement.repositories.UserRolesRepository;
import com.taskmanagement.services.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {


	@Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;

	public UserRoleServiceImpl() {
		super();
	}
    
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRolesRepository userRolesRepository) {
		super();
		this.userRoleRepository = userRoleRepository;
		this.userRolesRepository = userRolesRepository;
	}
    //POSTING USERROLE DATA
	 @Override
	 public ResponseEntity<Map<String, String>> createUserRole(UserRole userrole) 
	 {
		 Map<String, String> response = new HashMap<>();
	        if (userRoleRepository.existsById(userrole.getUserRoleID())) {
	            throw new UserRoleAlreadyExistsException("Userrole already exists");
	            
	        } else {
	            userRoleRepository.save(userrole);
	            response.put("code", "POSTSUCCESS");
	            response.put("message", "Userrole added successfully");
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        }
	    }
    
    
    
    //GETTING ALL USERROLES DATA
    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
    //GETTING USERROLE DATA BY ID
    @Override
    public UserRole getUserRoleById(int userRoleId) {
        return userRoleRepository.findById(userRoleId).orElse(null);
    }
    
    //UPDATING USERROLE BY ID
    @Override
    public boolean updateUserRoleById(int UserRoleID, UserRole updatedUserRole) {
    	
        Optional<UserRole> optionalUserrole = userRoleRepository.findById(UserRoleID);
        if (optionalUserrole.isPresent()) {
            UserRole existingUserrole = optionalUserrole.get();
            existingUserrole.setRoleName(updatedUserRole.getRoleName());
          
            userRoleRepository.save(existingUserrole);
            return true;
        }
        return false;
	}
    
    //DELETING USERROLE BY ID
    @Override
    public boolean deleteUserRoleById(int userRoleId) {
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userRoleId);
        if (optionalUserRole.isPresent()) {
        	userRolesRepository.deleteByUserRoleId(userRoleId);
            userRoleRepository.deleteById(userRoleId);
            return true;
        }
        return false;
    }
    
    
}
