package com.taskmanagement.bean;

import java.io.Serializable;

public class UserRolesId implements Serializable{
	private User user;
	private UserRole userRole;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public UserRolesId(User user, UserRole userRole) {
		super();
		this.user = user;
		this.userRole = userRole;
	}
	public UserRolesId() {
		super();
	}
	
}
