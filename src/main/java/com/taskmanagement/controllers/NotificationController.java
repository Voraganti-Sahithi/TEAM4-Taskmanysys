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
import org.springframework.web.bind.annotation.RestController;
import com.taskmanagement.bean.Notification;
import com.taskmanagement.exceptions.NoNotificationFoundException;
import com.taskmanagement.exceptions.NotificationAlreadyExistsException;
import com.taskmanagement.exceptions.NotificationDoesNotExistsException;
import com.taskmanagement.exceptions.NotificationListEmptyException;
import com.taskmanagement.exceptions.NotificationNotExistsException;
import com.taskmanagement.services.NotificationService;

@RestController
@RequestMapping("/api/notifications")
class NotificationController {
	@Autowired
    private  NotificationService notificationService;
	
	//INSERT DATA INTO NOTIFICION TABLE
	 @PostMapping("/post")
	    public ResponseEntity<String> createNotification(@RequestBody Notification notification) {
	        boolean created = notificationService.createNotification(notification);
	        if (created==false) {
	        	throw new NotificationAlreadyExistsException("Notification already exists");
	        }
	        	 
	            return ResponseEntity.status(HttpStatus.CREATED).body("{\"code\": \"POSTSUCCESS\", \"message\": \"Notification added successfully\"}");
	        } 
	 
	    
    // GET ALL DATA FROM NOTIFICAION TABLE
    @GetMapping("/all")
    public ResponseEntity<Object> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        if (notifications.isEmpty()) {
        	throw new NotificationListEmptyException("Notification list is empty");
           
        }
        return ResponseEntity.ok(notifications);    
        }
    
    
    // GET DATA BY NOTIFICATIONID FROM NOTIFICATION TABLE
    @GetMapping("/{notificationId}")
    public ResponseEntity<Object> getNotificationById(@PathVariable int notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        if (notification == null) {
            throw new NoNotificationFoundException("Notification doesn't exists");
        }
        return ResponseEntity.ok(notification);
    }
    
    
    // UPDATE DATA BY USING NOTIFICATIONID FROM NOTIFICATION TABLE
    @PutMapping("/update/{notificationId}")
    public ResponseEntity<Map<String,String>> updateNotification(@PathVariable int notificationId, @RequestBody Notification updatedNotification) {
    	boolean updated=notificationService.updateNotification(notificationId, updatedNotification);
        
        if (updated==false) {
        	throw new NotificationNotExistsException("Notification doesn't exists");
        	
        }
        else {
        	Map<String ,String> response=new HashMap<>();
            response.put(  "code","UPDATESUCCESS");
            response.put("message","Notification updated successfully");
            return ResponseEntity.ok(response);
           
        }
        	
    }
    
    
    // DELETE DATA BY USING NOTIFICATIONID FROM NOTIFICATION TABLE
    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<Object> deleteNotification(@PathVariable int notificationId) {
    	boolean deletednotification=notificationService.deleteNotification(notificationId);
        if (deletednotification == false) {
        	throw new NotificationDoesNotExistsException("Notification doesn't exists");
             
        }
        else {
        	Map<String ,String> response=new HashMap<>();
        	response.put(  "code","DELETESUCCESS");
            response.put("message","Notification deleted successfully");
            return ResponseEntity.ok(response);
        
    }
    }
    

}


