package com.taskmanagement.servicesimpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Project;
import com.taskmanagement.bean.Task;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.repositories.AttachmentRepository;
import com.taskmanagement.repositories.CommentRepository;
import com.taskmanagement.repositories.ProjectRepository;
import com.taskmanagement.repositories.TaskCategoryRepository;
import com.taskmanagement.repositories.TaskRepository;
import com.taskmanagement.services.ProjectService;

import jakarta.transaction.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
    private ProjectRepository projectRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private TaskCategoryRepository taskCategoryRepository;
public ProjectServiceImpl() {
		super();
	}
public ProjectServiceImpl(ProjectRepository projectRepository,TaskRepository taskRepository,AttachmentRepository attachmentRepository,CommentRepository commentRepository,TaskCategoryRepository taskCategoryRepository) {
		super();
		this.projectRepository = projectRepository;
		this.taskRepository=taskRepository;
		this.attachmentRepository=attachmentRepository;
		this.commentRepository=commentRepository;
		this.taskCategoryRepository=taskCategoryRepository;
	}

     //POST
    @Override
    public boolean createProject(Project project) {
	Optional<Project> project1 = projectRepository.findById(project.getProjectID());
	if(project1.isPresent())
		return false;
    // Save the project
    projectRepository.save(project);
    return true;
    }
    
      //GET ALLPROJECTS
	 @Override
	    public List<Project> getAllProjects() {
	        return projectRepository.findAll();
	    }
	 
    //GET ONGOING	 
    @Override
    public List<Project> getOngoingProjects() {
        LocalDate today = LocalDate.now();
        return projectRepository.findAllByStartDateBeforeAndEndDateAfter(today, today);
    }
	
    //GET DATERANGE
	@Override
    public List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        return projectRepository.findAllByStartDateBetween(startDate, endDate);
    }
	
	//GET ROLENAME
	@Override
    public List<Project> getProjectsByUserRole(String roleName) {
        return projectRepository.findByUserRoleRoleName(roleName);
    }
	
	//GET BYSTATUS
	@Override
    public List<Project> getProjectsByStatus(String status) {
	     return projectRepository.findByStatus(status);
     }
	
	//GET HIGHPRIORITYTASK
	 @Override
     public List<Project> getProjectsWithHighPriorityTasks() {
	List<Project> allProjects = projectRepository.findAll();
	return projectRepository.findProjectsWithHighPriorityTasks();
    }
	
	//UPDATE PROJECT DETAILS
	@Override
    public boolean updateProject(int projectId, Project updatedProject) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setProjectName(updatedProject.getProjectName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            existingProject.setUser(updatedProject.getUser());
            projectRepository.save(existingProject);
            return true;
        }
        return false;	 
	}
	
       //DELETE BY PROJECTID
     @Override
     public boolean deleteProjectById(int projectId) {
    	 Optional<Project> project = projectRepository.findById(projectId);
    	 
    	 if(project.isPresent())
    	 {
    		 attachmentRepository.deleteByProjectId(projectId);
    		 commentRepository.deleteByProjectId(projectId);
    		 taskCategoryRepository.deleteByProjectId(projectId);
    		 taskRepository.deleteByProjectId(projectId);
    		 projectRepository.deleteByProjectID(projectId);
    		 return true;
    	 }
    	 
          return false;  
     
     }     
}
