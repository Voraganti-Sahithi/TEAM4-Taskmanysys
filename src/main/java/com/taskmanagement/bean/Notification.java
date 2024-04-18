package com.taskmanagement.bean;

import java.time.LocalDateTime;


import jakarta.persistence.*;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @Column(name = "notificationid")
    private int notificationID;

    @Column(name = "Text",columnDefinition = "TEXT")
    private String text;

    @Column(name = "createdat", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL) // Add CascadeType.ALL
    @JoinColumn(name = "userid")
    private User user;

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Notification() {
	}

	public Notification(int notificationID, String text, LocalDateTime createdAt, User user) {
		this.notificationID = notificationID;
		this.text = text;
		this.createdAt = createdAt;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

    
}

