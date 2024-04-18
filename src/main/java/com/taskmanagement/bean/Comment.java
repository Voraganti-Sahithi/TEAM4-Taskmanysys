package com.taskmanagement.bean;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @Column(name = "commentid")
    private int commentID;

    @Column(name = "Text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "createdat", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskiD")
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "useriD")
    private User user;

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
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

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment(int commentID, String text, LocalDateTime createdAt, Task task, User user) {
		this.commentID = commentID;
		this.text = text;
		this.createdAt = createdAt;
		this.task = task;
		this.user = user;
	}

	public Comment() {
	}

    
}

