package com.taskmanagement.servicesimpl;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Project;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.User;
import com.taskmanagement.repositories.AttachmentRepository;
import com.taskmanagement.repositories.CommentRepository;
import com.taskmanagement.repositories.ProjectRepository;
import com.taskmanagement.repositories.TaskCategoryRepository;
import com.taskmanagement.repositories.TaskRepository;
import com.taskmanagement.repositories.UserRepository;
import com.taskmanagement.services.TaskService;

import jakarta.transaction.Transactional;
 

@Service
public class TaskServiceImpl implements TaskService {

	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private TaskCategoryRepository taskCategoryRepository;
	
	
	public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository,
			UserRepository userRepository, AttachmentRepository attachmentRepository, CommentRepository commentRepository,
			TaskCategoryRepository taskCategoryRepository) {
		super();
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.attachmentRepository = attachmentRepository;
		this.commentRepository = commentRepository;
		this.taskCategoryRepository = taskCategoryRepository;
	}

	public TaskServiceImpl() {
		super();
	}  
	
	
	//POSTING THE TASK DATA
	@Override
	public boolean addTaskWithProjectAndUser(int projectId, int userId, Task task) {
			
		Optional<Task> optionalTask = taskRepository.findById(task.getTaskID());
		if(optionalTask.isPresent())
				return false;
			
		// Fetch project and user from repository
	       Optional<Project> project = projectRepository.findById(projectId);
	       Optional<User> user = userRepository.findById(userId);

	     // Set project and user for the task
	       task.setProject(project.get());
	       task.setUser(user.get());

	     // Save the task
	       taskRepository.save(task);
	       return true;
			
		}
		
		
	
	 //GETTING TASKS WHICH ARE OVERDUE
     @Override
     public List<Task> getOverdueTasks() {
        
        LocalDate currentDate = LocalDate.now();
        return taskRepository.findByDueDateBeforeAndStatus(currentDate, "Pending");
     }
    
    
    //GETTING TASKS BASED ON PRIORITY AND STATUS
    @Override
    public List<Task> getTasksByPriorityAndStatus(String priority, String status) {
        
        return taskRepository.findByPriorityAndStatus(priority, status);
    }
    
    
    //GETTING TASKS WHICH ARE DUE-SOON
    @Override
    public List<Task> getTasksDueSoon() {
        
        LocalDate currentDate = LocalDate.now();
        LocalDate soonDate = currentDate.plusDays(7); // Example: Due soon within next 7 days
        return taskRepository.findByDueDateBetween(currentDate, soonDate);
    }
    
    
    //GETTING TASKS BY USERID AND STATUS
    @Override
    public List<Task> getTasksByUserAndStatus(int userId, String status) {
        
        return taskRepository.findByUserUserIDAndStatus(userId, status);
    }
    
    
    //GETTING TASKS BY CATEGORYID
    @Override
    public List<Task> getTasksByCategory(int categoryId) {
    	
        return taskRepository.findByCategoryId(categoryId);
    }
    
   
	//UPDATING TASK BY TASKID
    @Override
    public boolean updateTask(int taskId, Task taskDetails) {
        // Implementation to update task details
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTaskName(taskDetails.getTaskName());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setDueDate(taskDetails.getDueDate());
            existingTask.setPriority(taskDetails.getPriority());
            existingTask.setStatus(taskDetails.getStatus());
            existingTask.setProject(taskDetails.getProject());
            existingTask.setUser(taskDetails.getUser());
            taskRepository.save(existingTask);
            return true;
        } else {
            return false; // Task with given ID not found
        }
    }
    
    
    //DELETING TASK BY TASKID
	@Override
	@Transactional
	public boolean deleteTask(int taskId) {
		Optional<Task> task=taskRepository.findById(taskId);
		
		if(task.isPresent())
		{
			commentRepository.deleteByTaskId(taskId);
			attachmentRepository.deleteByTaskId(taskId);
			taskCategoryRepository.deleteByTaskId(taskId);
			taskRepository.deleteByTaskId(taskId);
			return true;
		}
		return false;
	}
	
	//RETRIEVING TASK BY ID
	public Task retrieveById(int taskId)
	{
		if(taskRepository.existsById(taskId)) {
			Optional<Task>  task= taskRepository.findById(taskId);
			return task.get();
		}
		return null;
	}
    

}
