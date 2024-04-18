package com.taskmanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.taskmanagement.bean.Project;

public interface ProjectService {
	
	boolean createProject(Project project);
	
	List<Project> getAllProjects();
	
    List<Project> getOngoingProjects();
   
    List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate);
    
    List<Project> getProjectsByUserRole(String roleName);
    
    List<Project> getProjectsByStatus(String status);
   
    List<Project> getProjectsWithHighPriorityTasks();
    
    boolean updateProject(int projectId, Project updatedProject); 
    
    boolean deleteProjectById(int projectId);
    
    
}

