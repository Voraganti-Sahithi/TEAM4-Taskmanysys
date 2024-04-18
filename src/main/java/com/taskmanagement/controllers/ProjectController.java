package com.taskmanagement.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.taskmanagement.bean.Project;
import com.taskmanagement.exceptions.ProjectAlreadyExistException;
import com.taskmanagement.exceptions.ProjectDoesNotExistsException;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.exceptions.ProjectNotExistsException;
import com.taskmanagement.services.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    public ProjectController() {
		super();
	}

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
    //POST 
	@PostMapping("/post")
	 public ResponseEntity<Map<String, String>> createProject(@RequestBody Project project){
		boolean b = projectService.createProject(project);
		if (b == false) {
	        throw new ProjectAlreadyExistException("Project alredy exist");
		}Map<String, String> response = new HashMap<>();
        response.put("code", "POSTSUCCESS");
        response.put("message", "Project added successfully");
	 	   return ResponseEntity.ok(response) ;
	    }
	
	
    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    
    
    //GET ONGING PROJECTS
    @GetMapping("/ongoing")
    public ResponseEntity<List<Project>> getOngoingProjects() {
        List<Project> ongoingProjects = projectService.getOngoingProjects();
        if(ongoingProjects.isEmpty()) {
        	throw new ProjectListEmptyException("Project List is Empty");	
        }
        return ResponseEntity.ok(ongoingProjects);
    }
    
    
    //GET SPECIFIC DATE RANGE
    @GetMapping("/date-range/{startDate}/{endDate}")
    public ResponseEntity<List<Project>> getProjectsByDateRange(
            @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        List<Project> projects = projectService.getProjectsByDateRange(startDate, endDate);
        if(projects.isEmpty()) {
        	throw new ProjectListEmptyException("Project List is Empty");	
        }
        return ResponseEntity.ok(projects);
    }
    
    
    //GET ROLENAME
    @GetMapping("/user-role/{roleName}")
    public List<Project> getProjectsByUserRole(@PathVariable String roleName) {
    	List<Project> projects = projectService.getAllProjects();
    	if(projects.isEmpty()) {
    		throw new ProjectListEmptyException("Project List is Empty");		
   	}
        return projectService.getProjectsByUserRole(roleName);  
    }
    
    
    //GET STATUS
    @GetMapping("/status/{status}")
    public List<Project> getProjectsByStatus(@PathVariable String status) {
    	List<Project> projects = projectService.getProjectsByStatus(status);
    	if(projects.isEmpty()) {
    		 throw new ProjectListEmptyException("Project List is Empty");
    	}
        return projectService.getProjectsByStatus(status);
    }
    
    
    //GET HIGH-PRIORITY-TASK
    @GetMapping("/high-priority-tasks")
    public List<Project> getProjectsWithHighPriorityTasks() {
    	List<Project> projects = projectService.getProjectsWithHighPriorityTasks();        
        if (projects.isEmpty()) {
        	throw new ProjectListEmptyException("Project List is Empty");
        }
        return projectService.getProjectsWithHighPriorityTasks();
    }
    
    
    //UPDATE PROJECTID
    @PutMapping("/update/{projectId}")
    public ResponseEntity<Map<String, String>> updateProject(@PathVariable int projectId, @RequestBody Project updatedProject) {
        boolean updated = projectService.updateProject(projectId, updatedProject);
        if (updated==false) {
        	throw new ProjectNotExistsException("Project doesn't exist exist");	
        } 
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "Project updated successfully");
        return ResponseEntity.ok(response);        
     }
   
    
    //DELETE PROJECTID
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable int projectId) {
    	boolean b = projectService.deleteProjectById(projectId);
    	if(b==false)
    		throw new ProjectDoesNotExistsException("Project doesn't exist exist");
    	Map<String, String> response = new HashMap<>();
        response.put("code", "DELETESUCCESS");
        response.put("message", "Project deleted successfully");
        return ResponseEntity.ok(response);	    
    }    
}
