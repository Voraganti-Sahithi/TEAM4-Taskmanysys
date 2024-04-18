package com.taskmanagement.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "userrole")
public class UserRole {
    @Id
    @Column(name = "userroleID")
    private int userRoleID;

    @Column(name = "rolename")
    private String roleName;


	public int getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRole(int userRoleID, String roleName) {
		this.userRoleID = userRoleID;
		this.roleName = roleName;
	}

	public UserRole() {
	}


    
}

