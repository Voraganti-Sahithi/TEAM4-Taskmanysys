package com.taskmanagement.servicesimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.User;
import com.taskmanagement.exceptions.UserAlreadyExistsException;
import com.taskmanagement.repositories.CommentRepository;
import com.taskmanagement.repositories.NotificationRepository;
import com.taskmanagement.repositories.ProjectRepository;
import com.taskmanagement.repositories.TaskRepository;
import com.taskmanagement.repositories.UserRepository;
import com.taskmanagement.repositories.UserRolesRepository;
import com.taskmanagement.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
    private UserRepository userRepository;  
	@Autowired
    private CommentRepository commentRepository;  
	@Autowired
    private TaskRepository taskRepository;  
	@Autowired
    private ProjectRepository projectRepository;  
	@Autowired
    private NotificationRepository notificationRepository;  
	@Autowired
	private UserRolesRepository userRolesRepository;
	
    public UserServiceImpl() {
		super();
	}

    public UserServiceImpl(UserRepository userRepository, CommentRepository commentRepository,
			TaskRepository taskRepository, ProjectRepository projectRepository,
			NotificationRepository notificationRepository,UserRolesRepository userRolesRepository) {
		super();
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.taskRepository = taskRepository;
		this.projectRepository = projectRepository;
		this.notificationRepository = notificationRepository;
		this.userRolesRepository = userRolesRepository;
	}
	
	
	//GETTING USERS BASED ON MOST-TASKS
    @Override
    public List<User> getUsersWithMostTasks() {
        return userRepository.findUsersWithMostTasks();
    }
    
    
  //GETTING USERS BASED ON COMPLETED-TASKS
    @Override
    public List<User> getUsersWithCompletedTasks() {
        return userRepository.findUsersWithCompletedTasks();
    }

    //GET DATA FROM USER TABLE BY USING EMAIL-DOMAIN
      @Override
	public List<User> getUsersByDomain(String domain) {
		
		return userRepository.findByEmailContaining("@" + domain);
	}

      
      //AUTHENTICATE USER DETAILS BY USING USERNAME AND PASSWORD
	@Override
	public boolean authenticateUser(String username, String password) {
		

		User user = userRepository.findByusernameAndPassword(username,password);
		
		return user!=null;
		
	}
	//POSTING USER DATA 
  	 @Override
  	 public ResponseEntity<Map<String, String>> createUser(User user) 
  	 {
  		 Map<String, String> response = new HashMap<>();
   
  	        if (userRepository.existsById(user.getUserID())) {
  	            throw new UserAlreadyExistsException("User already exists");
  	            
  	        } else {
  	            userRepository.save(user);
  	            response.put("code", "POSTSUCCESS");
  	            response.put("message", "User added successfully");
  	            return new ResponseEntity<>(response, HttpStatus.CREATED);
  	        }
  	    }
  	 //GETTING ALL USER DATA
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //GETTING USER BY USER ID
    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
    //GETTING USER DATA BY USER  NAME
    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByFullNameIgnoreCase(name);
    }
    //UPDATING USER DATA BY ID
    @Override
    public boolean updateUser(int userId, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFullName(updatedUser.getFullName());
            
            userRepository.save(existingUser);
            return true;
        }
        return false;
	}

   
    
    //DELETING USER BY USERID
    @Override
	@Transactional
	public boolean deleteUser(int userId) {
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent())
		{
			commentRepository.deleteByUserId(userId);
			notificationRepository.deleteByUserId(userId);
			taskRepository.deleteByUserId(userId);
			projectRepository.deleteByUserId(userId);
			userRolesRepository.deleteByUserId(userId);
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}
    

}
