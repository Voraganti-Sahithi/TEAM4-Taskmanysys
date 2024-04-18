package com.taskmanagement.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Task;
import com.taskmanagement.exceptions.NoCategoryFoundException;
import com.taskmanagement.exceptions.NoTaskFoundException;
import com.taskmanagement.exceptions.TaskCategoryAlreadyExistsException;
import com.taskmanagement.services.TaskCategoryService;
 
@RestController
@RequestMapping("/api/taskcategories")
public class TaskCategoryController {

    private TaskCategoryService taskCategoryService;

    public TaskCategoryController(TaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }
    
    //POSTING THE TASKCATEGORY DATA
    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> associateTaskWithCategory(@RequestBody Map<String,Integer> requestBody)
    {
    	int taskId = requestBody.get("taskId");
    	int categoryId = requestBody.get("categoryId");
    	
    	if(taskCategoryService.associateTaskWithCategory(taskId,categoryId))
    		return ResponseEntity.ok().body(new SuccessResponse("Task-Category added successfully","POSTSUCCESS"));
    	throw new TaskCategoryAlreadyExistsException("Task-Category Already Exists");
			
    }
    
    
    //GETTING CATEGORY DATA BY TASKID
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Category>> getAllCategoriesForTask(@PathVariable int taskId) {
        List<Category> categories = taskCategoryService.getAllCategoriesForTask(taskId);
        if(categories.isEmpty())
        	throw new NoCategoryFoundException("No category found for a particular task");
        return ResponseEntity.ok(categories);
    }
    
    
    //GETTING TASK DATA BY CATEGORYID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getAllTasksForCategory(@PathVariable int categoryId) {
        List<Task> tasks = taskCategoryService.getAllTasksForCategory(categoryId);
        if(tasks.isEmpty())
        	throw new NoTaskFoundException("No task found for a particular category");
        return ResponseEntity.ok(tasks);
    }
}
