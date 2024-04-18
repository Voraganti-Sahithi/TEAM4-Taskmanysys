package com.taskmanagement.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.taskmanagement.bean.Task;
import com.taskmanagement.exceptions.TaskAlreadyExistsException;
import com.taskmanagement.exceptions.TaskListEmptyException;
import com.taskmanagement.exceptions.TaskNotExistsException;
import com.taskmanagement.exceptions.TaskNotExistsExistsException;
import com.taskmanagement.services.TaskService;
 
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
 
	@Autowired
    private TaskService taskService;
	
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    
    //POSTING THE TASK DATA
    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createTask(@RequestParam("projectId") int projectId,
            @RequestParam("userId") int userId,
            @RequestBody Task task) {
    	
    	boolean b = taskService.addTaskWithProjectAndUser(projectId, userId, task);
    	if(b==false)
    		throw new TaskAlreadyExistsException("Task already exists");
    	
        return ResponseEntity.ok().body(new SuccessResponse("Task added Successfully","POSTSUCCESS"));
    }
    
    
    //GETTING TASKS WHICH ARE OVERDUE
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> overdueTasks = taskService.getOverdueTasks();
        if(overdueTasks.isEmpty())
        	throw new TaskListEmptyException("Task list is empty");
        return ResponseEntity.ok(overdueTasks);
    }
    
    
    //GETTING TASKS BASED ON PRIORITY AND STATUS
    @GetMapping("/priority/{priority}/status/{status}")
    public ResponseEntity<List<Task>> getTasksByPriorityAndStatus(@PathVariable String priority, @PathVariable String status) {
        List<Task> tasks = taskService.getTasksByPriorityAndStatus(priority, status);
        if(tasks.isEmpty())
        	throw new TaskListEmptyException("Task list is empty");
        return ResponseEntity.ok(tasks);
    }
    
    
    //GETTING TASKS WHICH ARE DUE-SOON
    @GetMapping("/due-soon")
    public ResponseEntity<List<Task>> getTasksDueSoon() {
        List<Task> tasksDueSoon = taskService.getTasksDueSoon();
        if(tasksDueSoon.isEmpty())
        	throw new TaskListEmptyException("Task list is empty");
        return ResponseEntity.ok(tasksDueSoon);
    }
    
    
    //GETTING TASKS BY USERID AND STATUS
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<Task>> getTasksByUserAndStatus(
            @PathVariable int userId, @PathVariable String status) {
        List<Task> tasks = taskService.getTasksByUserAndStatus(userId, status);
        if(tasks.isEmpty())
        	throw new TaskListEmptyException("Task list is empty");
        return ResponseEntity.ok(tasks);
    }
    
    
    //GETTING TASKS BY CATEGORYID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable int categoryId) {
        List<Task> tasks = taskService.getTasksByCategory(categoryId);
        if(tasks.isEmpty())
        	throw new TaskListEmptyException("Task list is empty");
        return ResponseEntity.ok(tasks);
    }
    
    
    //UPDATING TASK BY TASKID
    @PutMapping("/update/{taskId}")
    public ResponseEntity<SuccessResponse> updateTask(@PathVariable int taskId,
                                                          @RequestBody Task taskDetails) {
        boolean updated = taskService.updateTask(taskId, taskDetails);
        if (updated==false) {
            throw new TaskNotExistsException("Task doesn't exist");
        } 
        return ResponseEntity.ok().body(new SuccessResponse("Task updated successfully","UPDATESUCCESS"));
    }
    
    
    //DELETING TASK BY TASKID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable int taskId) {
        boolean deleted = taskService.deleteTask(taskId);
        if (deleted==false) {
        	throw new TaskNotExistsExistsException("Task doesn't exist");
        } 
        Map<String, String> response = new HashMap<>();
        response.put("code", "DELETESUCCESS");
        response.put("message", "Task deleted successfully");
        return ResponseEntity.ok(response);
    }
}
   

