package com.taskmanagement.thymleafcontroller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.Project;
import com.taskmanagement.exceptions.ProjectAlreadyExistException;
import com.taskmanagement.exceptions.ProjectDoesNotExistsException;
import com.taskmanagement.exceptions.ProjectListEmptyException;
import com.taskmanagement.exceptions.ProjectNotExistsException;
import com.taskmanagement.services.ProjectService;

@Controller

public class ProjectThymeleafController {
     @Autowired
     private ProjectService projectService; 
     
//   ---------------------------------------------------------
//   -------------------------------------------------------------  
     @GetMapping("/api1/projectHome")
     public String showUserHomePage() {
     	return "homeProject";
 	}
     
     @GetMapping("/api1/ongoing")
     public String showongoingform() {
    	 return "ongoingform";
     }
   //GET ONGOING PROJECTS
     @GetMapping("/projects/ongoing")
     public String getOngoingProjects(Model model) {
         List<Project> ongoingProjects = projectService.getOngoingProjects();
         if(ongoingProjects.isEmpty()) {
         	throw new ProjectListEmptyException("Project List is Empty");	
         }
         model.addAttribute("ongoingProjects", ongoingProjects);
         return "ongoingdetails";
     }
     
//--------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------
     
     @GetMapping("/daterange")
     public String showDateRange() {
    	 return "DateRange";
     }
   //GET SPECIFIC DATE RANGE
     @GetMapping("/date-range")
     public String getProjectsByDateRange(
             @RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,Model model) {
         List<Project> projects = projectService.getProjectsByDateRange(startDate, endDate);
         if(projects.isEmpty()) {
         	throw new ProjectListEmptyException("Project List is Empty");	
         }
         model.addAttribute("Project",projects);
         return "Project-Details";
     }
//---------------------------------------------------------
//-------------------------------------------------
  
     
 	@GetMapping("/api1/post")
	public String createProject(Model model)
	{
		Project p=new Project();
		model.addAttribute("project", p);
		return "ProjectPostForm";
	}
	// code for posting the data
	@PostMapping("api1/project/post")
	 public String createProject(Model model,Project project) throws ProjectAlreadyExistException
	 {	
		model.addAttribute("project",project);
		projectService.createProject(project);
		return "ProjectPostDetails";
	 }
// --------------------------------------------------------------
//---------------------------------------------------------------
	@GetMapping("/api1/userrole")
	public String showrolename()
	{
		return "RoleNameForm";
		
	}
	
//	@GetMapping("/api1/user-role")
//    public String getProjectsByUserRole(@RequestParam("roleName") String roleName, Model model) {
//        List<Object[]> projects = projectService.getProjectsByUserRole(roleName);
//        if (projects.isEmpty()) {
//            throw new ProjectListEmptyException("Project List is Empty");
//        }
//        model.addAttribute("projects", projects.get(0)[0]);
//        model.addAttribute("projects1", projects.get(0)[1]);
//        return "RoleNameDetails"; 
//    }
	
//--------------------------------------------------------------------------
//----------------------------------------------------------------------------	
	@GetMapping("/api1/status")
	public String showstatus()
	{
		return "StatusForm";
		
	}
	 //GET STATUS
//    @GetMapping("/status")
//    public String getProjectsByStatus(@RequestParam("status") String status,Model model) {
//    	List<Object[]> projects = projectService.getProjectsByStatus(status);
//    	if(projects.isEmpty()) {
//    		 throw new ProjectListEmptyException("Project List is Empty");
//    	}
//    	 model.addAttribute("projects", projects.get(0)[0]);
//         model.addAttribute("projects1", projects.get(0)[1]);        
//         return "StatusDetails";    	       
//    }
//-------------------------------------------------------------------------------------------	
//	-------------------------------------------------------------------------------------------
    
    @GetMapping("/api1/highprioritytasks")
    public String showhighprioritytasks()
    {
    	return "highpriorityForm";
    }
    
  //GET HIGH-PRIORITY-TASK
//    @GetMapping("/high-priority-tasks")
//    public String getProjectsWithHighPriorityTasks(Model model) {
//    	List<Object[]> projects = projectService.getProjectsWithHighPriorityTasks();        
//        if (projects.isEmpty()) {
//        	throw new ProjectListEmptyException("Project List is Empty");
//        }
//        model.addAttribute("projects", projects.get(0)[0]);
//        model.addAttribute("projects1", projects.get(0)[1]);  
//        return "highpriorityDetails";
//    }
    
//---------------------------------------    
//----------------------------------------    
       
    @GetMapping("/api1/update")
    public String showpostUpdateForm(Model model) {
    	Project project=new  Project();
    	model.addAttribute("Project", project);
		return "UpdateForm";
    	
    } 
    //UPDATE PROJECTID
    @GetMapping("/update")
    public String updateProject(@RequestParam("projectID") int projectId, @ModelAttribute("Update") Project updatedProject,Model model) {
        boolean updated = projectService.updateProject(projectId, updatedProject);
        if (updated==false) {
        	throw new ProjectNotExistsException("Project doesn't exist exist");	
        } 
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "Project updated successfully");
        model.addAttribute("project", updated);
        return "UpdateDetails";        
     }
  //------------------------------------------------------------
    //------------------------------------------------------------
    @GetMapping("/api1/delete")
    public String showdeleteForm(Model model) {
    	return "ProjectDeleteForm";
    }    
    //DELETE PROJECTID
    @GetMapping("/delete")
    public String deleteProject(@RequestParam("projectID") int projectId,Model model) {
    	boolean b = projectService.deleteProjectById(projectId);
    	if(b==false)
    		throw new ProjectDoesNotExistsException("Project doesn't exist exist");
    	Map<String, String> response = new HashMap<>();
        response.put("code", "DELETESUCCESS");
        response.put("message", "Project deleted successfully");
        model.addAttribute("Success", b);
        return "ProjectDeleteDetails";	    
    }
	
    
}
