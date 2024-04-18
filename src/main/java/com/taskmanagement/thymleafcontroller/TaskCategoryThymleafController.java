package com.taskmanagement.thymleafcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Task;
import com.taskmanagement.exceptions.NoCategoryFoundException;
import com.taskmanagement.exceptions.NoTaskFoundException;
import com.taskmanagement.exceptions.TaskCategoryAlreadyExistsException;
import com.taskmanagement.services.TaskCategoryService;
 
@Controller
@RequestMapping("/api1")
public class TaskCategoryThymleafController {

	@Autowired
	private TaskCategoryService taskCategoryService;

	@GetMapping("/taskCategoryHome")
    public String showTaskCategoryHomePage() {
    	return "homeTaskCategory";
    }
    
    //GET TASK ID THROUGH FROM
    @GetMapping("/taskcategories1/task")
    public String getTaskId() {
    	return "taskform";
    }
    
    
    //GETTING CATEGORY DATA BY TASKID
    @GetMapping("/taskcategories1/task/taskId")
    public String getAllCategoriesForTask(@RequestParam("taskId") int taskId,Model model) {
        List<Category> categories = taskCategoryService.getAllCategoriesForTask(taskId);
        try {
            if (categories.isEmpty()) {
            	throw new NoCategoryFoundException("No Category found for the entered taskId");
            }
            
        } catch (NoCategoryFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("categories", categories);
        return "categories";
    }
    
    
    // Method for displaying the form
    @GetMapping("/taskcategories1/associate")
    public String showAssociateTaskWithCategoryForm() {
        return "postform";
    }
    
    
    @PostMapping("/taskcategories1/post")
    public String associateTaskWithCategory(@RequestParam("taskId") int taskId,
                                            @RequestParam("categoryId") int categoryId,
                                            Model model) {
        try {
            if (taskCategoryService.associateTaskWithCategory(taskId, categoryId)) {
                model.addAttribute("successMessage", "Task-Category added successfully");
                return "success"; 
            }
            throw new TaskCategoryAlreadyExistsException("Task-Category Already Exists");
        } catch (TaskCategoryAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
    }

    // Method for displaying the success page
    @GetMapping("/taskcategories1/success")
    public String showSuccessPage() {  
        return "success";
    }

    // Method for displaying the error page
    @GetMapping("/taskcategories1/error")
    public String showErrorPage() {
        return "error";
    }
    
    
  //GET TASK ID THROUGH FROM
    @GetMapping("/taskcategories1/category")
    public String getcategoryId() {
    	return "categoryform";
    }
    
    
    //GETTING TASK DATA BY CATEGORYID
    @GetMapping("/taskcategories1/category/categoryId")
    public String getAllTasksForCategory(@RequestParam("categoryId") int categoryId,Model model) {
        List<Task> tasks = taskCategoryService.getAllTasksForCategory(categoryId);
        try {
            if (tasks.isEmpty()) {
            	throw new NoTaskFoundException("No Task found for the entered categoryId");
            }
            
        } catch (NoTaskFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}