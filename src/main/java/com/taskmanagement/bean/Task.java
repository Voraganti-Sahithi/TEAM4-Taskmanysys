package com.taskmanagement.bean;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Task")
public class Task {
    @Id
    @Column(name = "TaskID")
    private int taskID;

    @Column(name = "taskname")
    private String taskName;

    @Column(name = "Description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "duedate")
    private LocalDate dueDate;

    @Column(name = "Priority", length = 20)
    private String priority;

    @Column(name = "Status", length = 20)
    private String status;

    @ManyToOne(cascade = CascadeType.ALL) // CascadeType.ALL will cascade all operations
    @JoinColumn(name = "ProjectID")
    private Project project; // Many-to-one relationship with Project

    @ManyToOne(cascade = CascadeType.ALL) // CascadeType.ALL will cascade all operations
    @JoinColumn(name = "UserID")
    private User user; // Many-to-one relationship with User


	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Task(int taskID, String taskName, String description, LocalDate dueDate, String priority, String status,
			Project project, User user) {
		this.taskID = taskID;
		this.taskName = taskName;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.status = status;
		this.project = project;
		this.user = user;
		
	}

	public Task() {

	}

   
}
