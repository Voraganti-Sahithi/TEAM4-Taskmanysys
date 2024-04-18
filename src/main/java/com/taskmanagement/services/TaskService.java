package com.taskmanagement.services;

import java.util.List;

import com.taskmanagement.bean.Task;

public interface TaskService {
	
	boolean addTaskWithProjectAndUser(int projectId, int userId, Task task);
	
	List<Task> getOverdueTasks();
	
	List<Task> getTasksByPriorityAndStatus(String priority, String status);
	
	List<Task> getTasksDueSoon();
	
	List<Task> getTasksByUserAndStatus(int userId, String status);
	
	List<Task> getTasksByCategory(int categoryId);
	
	boolean updateTask(int taskId, Task taskDetails);
	
	boolean deleteTask(int taskId);

	Task retrieveById(int taskId);
	


}
