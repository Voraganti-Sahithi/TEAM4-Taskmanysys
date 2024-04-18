package com.taskmanagement.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.TaskCategory;
import com.taskmanagement.bean.TaskCategoryId;
import com.taskmanagement.repositories.CategoryRepository;
import com.taskmanagement.repositories.TaskCategoryRepository;
import com.taskmanagement.repositories.TaskRepository;
import com.taskmanagement.services.TaskCategoryService;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private TaskCategoryRepository taskCategoryRepository;
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;
    
    public TaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository, TaskRepository taskRepository,
			CategoryRepository categoryRepository) {
		super();
		this.taskCategoryRepository = taskCategoryRepository;
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}

  //POSTING THE TASKCATEGORY DATA
  	@Override
  	public boolean associateTaskWithCategory(int taskId, int categoryId) {
          // Fetch task entity by ID
          Optional<Task> task = taskRepository.findById(taskId);
         
          // Fetch category entity by ID
          Optional<Category> category = categoryRepository.findById(categoryId);
          
          TaskCategory taskCategory = new TaskCategory(task.get(),category.get());
          
          //TaskCategoryId id = new TaskCategoryId(task.get(), category.get());
          
          TaskCategory tc = taskCategoryRepository.findByTaskAndCategory(task.get(),category.get());
          
          for(TaskCategory tc1:taskCategoryRepository.findAll())
          {
          	if(tc1.equals(tc))
          		return false;
          }
          
          taskCategoryRepository.save(taskCategory);
          return true; 
      }
  	
  	
    //GETTING CATEGORY DATA BY TASKID
    @Override
    public List<Category> getAllCategoriesForTask(int taskId) {
        List<TaskCategory> taskCategories = taskCategoryRepository.findByTaskTaskID(taskId);
        List<Category> categories = new ArrayList<>();
        for (TaskCategory taskCategory : taskCategories) {
            categories.add(taskCategory.getCategory());
        }
        return categories;
    }
    
    
  //GETTING TASK DATA BY CATEGORYID
    @Override
    public List<Task> getAllTasksForCategory(int categoryId) {
        List<TaskCategory> taskCategories = taskCategoryRepository.findByCategoryCategoryID(categoryId);
        List<Task> tasks = new ArrayList<>();
        for (TaskCategory taskCategory : taskCategories) {
            tasks.add(taskCategory.getTask());
        }
        return tasks;
    }

}

