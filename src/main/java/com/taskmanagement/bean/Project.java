package com.taskmanagement.bean;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Project")
public class Project {
    @Id
    @Column(name = "ProjectID")
    private int projectID;

    @Column(name = "projectname")
    private String projectName;

    @Column(name = "Description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL) // Add CascadeType.ALL
    @JoinColumn(name = "UserID")
    private User user;

    

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Project() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project(int projectID, String projectName, String description, LocalDate startDate, LocalDate endDate, User user) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

    
}

