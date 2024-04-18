package com.taskmanagement.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "userroles")
@IdClass(UserRolesId.class)
public class UserRoles {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userroleid")
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

	public UserRoles(User user, UserRole userRole) {

		this.user = user;
		this.userRole = userRole;
	}

	public UserRoles() {
	}

    
}

