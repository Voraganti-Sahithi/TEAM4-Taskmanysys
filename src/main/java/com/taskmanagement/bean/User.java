package com.taskmanagement.bean;

import jakarta.persistence.*;


@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "UserID")
    private int userID;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "fullname")
    private String fullName;


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	

	public User(int userID, String username, String password, String email, String fullName)
	{
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		
	}

	public User() {
	}

    
}

