package com.taskmanagement.services;
 
import java.util.List;
import com.taskmanagement.bean.UserRoles;
 
public interface UserRolesService {
	
	UserRoles assignUserRole(UserRoles userRoles);
	
    List<UserRoles> getAllUserRoles();
   
    List<UserRoles> getUserRolesByUserId(int userId);
   
    boolean deleteUserRoles(int userid, int userRoleId );
}