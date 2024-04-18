package com.taskmanagement.servicesimpl;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.User;
import com.taskmanagement.bean.UserRole;
import com.taskmanagement.bean.UserRoles;
import com.taskmanagement.repositories.UserRolesRepository;
import com.taskmanagement.services.UserRolesService;

import jakarta.transaction.Transactional;

@Service
public class UserRolesServiceImpl implements UserRolesService{
	@Autowired
    private UserRolesRepository userRolesRepository;
	
	//POST 
	@Override
    public UserRoles assignUserRole(UserRoles userRoles) {
        return userRolesRepository.save(userRoles);
    }
	
	//GET USERROLE
    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    //GET USERROLEID
    @Override
    public List<UserRoles> getUserRolesByUserId(int userId) {
        return userRolesRepository.findByUserUserID(userId);
    }
    
  //DELETE BY USERID, USERROLEID
    @Transactional
    @Override
	public boolean deleteUserRoles(int userId,int userRoleId) {
    	if (!userRolesRepository.existsByUserRoleUserRoleIDAndUserUserID(userRoleId, userId)) 
            return false;
		 userRolesRepository.deleteUserRoleByUserIdAndRoleId(userId, userRoleId);
		 return true;
    }
}
