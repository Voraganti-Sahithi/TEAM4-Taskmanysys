package com.taskmanagement.services;

import java.util.List;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.TaskCategory;

public interface TaskCategoryService
{
    boolean associateTaskWithCategory(int taskId,int categoryId);
    
    List<Task> getAllTasksForCategory(int categoryId);
    
    List<Category> getAllCategoriesForTask(int taskId);
    
    
    
  
    
}

