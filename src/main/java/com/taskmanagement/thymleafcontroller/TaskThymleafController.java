package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
 
import com.taskmanagement.bean.Task;
import com.taskmanagement.exceptions.NoTaskFoundException;
import com.taskmanagement.exceptions.TaskAlreadyExistsException;
import com.taskmanagement.exceptions.TaskCategoryAlreadyExistsException;
import com.taskmanagement.exceptions.TaskListEmptyException;
import com.taskmanagement.exceptions.TaskNotExistsException;
import com.taskmanagement.exceptions.TaskNotExistsExistsException;
import com.taskmanagement.services.TaskService;
 
@Controller
@RequestMapping("/api1")
public class TaskThymleafController {
 
	@Autowired
    private TaskService taskService;
	
    public TaskThymleafController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    // Method for displaying the form
    @GetMapping("tasks1/taskHome")
    public String showTaskHomePage() {
        return "homeTask";
    }
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    // Method for displaying the form
    @GetMapping("/tasks1/taskform")
    public String showTasksForm() {
        return "taskspostform";
    }
    
    
    //POSTING THE TASK DATA
    @PostMapping("/tasks1/post")
    public String createTask(@RequestParam("projectId") int projectId,
            @RequestParam("userId") int userId,
            @ModelAttribute("task_post") Task task,Model model) {
         
        try {
               if (taskService.addTaskWithProjectAndUser(projectId, userId, task)) {
                    model.addAttribute("successMessage", "Task added successfully");
                    return "success"; 
              }
              throw new TaskAlreadyExistsException("Task Already Exists");
        }    	
        catch (TaskAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        catch(Exception ex)
        {
        	model.addAttribute("errorMessage", ex.getMessage());
            return "error"; 
        }
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    
    //GETTING TASKS WHICH ARE OVERDUE
    @GetMapping("/tasks1/overdue")
    public String getOverdueTasks(Model model) {
        List<Task> overdueTasks = taskService.getOverdueTasks();   
        try {
            if (overdueTasks.isEmpty()) {
            	throw new TaskListEmptyException("Task list is empty");
            }
            
        } catch (TaskListEmptyException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("overduetasks", overdueTasks);
        return "overdue-tasks";
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //GET STATUS AND PRIORITY THROUGH FROM
    @GetMapping("/tasks1/priorityform")
    public String getTaskPriorityStatus() {
    	return "priority-status-form";
    }
    
    
    //GETTING TASKS BASED ON PRIORITY AND STATUS
    //http://localhost:9999/api/tasks1/priority/status?priority=high&status=pending
    @GetMapping("/tasks1/priority/status")
    public String getTasksByPriorityAndStatus(@RequestParam("priority") String priority, @RequestParam("status") String status,Model model) {
        List<Task> tasks = taskService.getTasksByPriorityAndStatus(priority, status);
        try {
            if (tasks.isEmpty()) {
            	throw new TaskListEmptyException("No such tasks exists with given priority and status");
            }
            
        } catch (TaskListEmptyException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("tasks", tasks);
        return "priority-status-tasks";
    }
     
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //GETTING TASKS WHICH ARE DUE-SOON
    @GetMapping("/tasks1/due-soon")
    public String getTasksDueSoon(Model model) {
        List<Task> tasksDueSoon = taskService.getTasksDueSoon();
        try {
            if (tasksDueSoon.isEmpty()) {
            	throw new TaskListEmptyException("Task list is empty");
            }
            
        } catch (TaskListEmptyException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("duesoontasks", tasksDueSoon);
        return "duesoon-tasks";
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //GET USERID AND STATUS THROUGH FROM
    @GetMapping("/tasks1/userform")
    public String getTaskUserStatus() {
    	return "user-status-form";
    }
    
    
    //GETTING TASKS BY USERID AND STATUS
    //http://localhost:9999/api/tasks1/user/status?userID=1&status=pending
    @GetMapping("/tasks1/user/status")
    public String getTasksByUserAndStatus(
            @RequestParam("userID") int userID, @RequestParam("status") String status,Model model) {
        List<Task> tasks = taskService.getTasksByUserAndStatus(userID, status);
        
        try {
            if (tasks.isEmpty()) {
            	throw new TaskListEmptyException("No such tasks exists with given userId and status");
            }
            
        } catch (TaskListEmptyException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("tasks", tasks);
        return "user-status-tasks";
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //GETTING CATEGORYID THROUGH FROM
    @GetMapping("/tasks1/categoryform")
    public String getTaskCategoryId() {
    	return "task-category-form";
    }
    
    
    //GETTING TASKS BY CATEGORYID
    //http://localhost:9999/api/tasks1/category/categoryId?categoryID=3
    @GetMapping("/tasks1/category/categoryID")
    public String getTasksByCategory(@RequestParam("categoryID") int categoryID,Model model) {
        List<Task> tasks = taskService.getTasksByCategory(categoryID);
        try {
            if (tasks.isEmpty()) {
            	throw new TaskListEmptyException("No such tasks exists with given categoryId");
            }
            
        } catch (TaskListEmptyException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("tasks", tasks);
        return "task-category-tasks";
        
        
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //FORM FOR RETRIEVING TASKID TO UPDATE
    @GetMapping("/tasks1/updateform/taskId")
    //http://localhost:9999/api/tasks1/updateform?taskId=3
    public String showctaskIdtoUpdate() {
        return "task-put-id";
    }
    
    
    @GetMapping("/tasks1/updateform")
    public String showUpdate(@RequestParam("taskId") int taskId,Model model) {
    	try
    	{
    		Task task = taskService.retrieveById(taskId);
    		if(task!=null)
    		{
    			model.addAttribute("task", task);
    	        return "task-put-form";
    		}
    		throw new TaskNotExistsException("Task doesn't exist");
    	}
    	catch (TaskNotExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
    	
    }
    
    
    //UPDATING TASK BY TASKID
    @GetMapping("/tasks1/update")
    public String updateTask(@RequestParam("taskID") int taskId,@ModelAttribute("task_post") Task taskDetails,Model model) {
        try {
            if (taskService.updateTask(taskId, taskDetails)) {
                 model.addAttribute("successMessage", "Task updated successfully");
                 return "success"; 
           }
           throw new TaskNotExistsException("Task doesn't exist");
     }    	
     catch (TaskNotExistsException e) {
         model.addAttribute("errorMessage", e.getMessage());
         return "error"; 
     }
     catch(Exception ex)
     {
     	model.addAttribute("errorMessage", ex.getMessage());
         return "error"; 
     }
    }
    
    
    
    //-------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------
    
    
    
    //GETTING TASKID TO DELETE THROUGH FROM
    @GetMapping("/tasks1/deleteform")
    public String getTaskId() {
    	return "task-delete-form";
    }
    
    
    //DELETING TASK BY TASKID
    @GetMapping("/tasks1/delete/taskId")
    public String deleteTask(@RequestParam("taskID") int taskID,Model model) {    
         try {
            if (taskService.deleteTask(taskID)) {
                 model.addAttribute("successMessage", "Task deleted successfully");
                 return "success"; 
           }
           throw new TaskNotExistsExistsException("There is no task with the entered taskID");
         }    	
	     catch (TaskNotExistsExistsException e) {
	         model.addAttribute("errorMessage", e.getMessage());
	         return "error"; 
	     }
	     catch(Exception ex)
	     {
	     	model.addAttribute("errorMessage", ex.getMessage());
	         return "error"; 
	     }
    }
}
   

