
package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taskmanagement.exceptions.NoNotificationFoundException;
import com.taskmanagement.exceptions.NotificationAlreadyExistsException;
import com.taskmanagement.exceptions.NotificationListEmptyException;
import com.taskmanagement.exceptions.NotificationNotExistsException;
import com.taskmanagement.services.NotificationService;
import com.taskmanagement.services.UserService;
import com.taskmanagement.bean.Notification;
import com.taskmanagement.bean.User;

@Controller
@RequestMapping("/api1")
public class NotificationThymeleafController {
	@Autowired
	NotificationService notificationService;
	UserService userservice;
	
	//CREATE NOTIFICATION
	@PostMapping("/create/notification")
    public String createNotification(@ModelAttribute("notification") Notification notification, Model model) {
        boolean created = notificationService.createNotification(notification);
        if (!created) {
            throw new NotificationAlreadyExistsException("Notification already exists");
        }
        model.addAttribute("code", "POSTSUCCESS");
        model.addAttribute("message", "Notification added successfully");
        return "notificationCreated"; // Return the name of the success view
    }

    @GetMapping("/create/notification")
    public String showNotificationForm(Model model) throws NotificationAlreadyExistsException {
        model.addAttribute("notification", new Notification());
        return "post-Notification"; // Return the name of the form view
    }

			
    //GET NOTIFICATION DETAILS
    @GetMapping("/notification")
    public String Notification() {
    	return "Get-Notification";
    	}
			
			
    @GetMapping("/notification/{notificationId}")
    public String getNotificationById(@RequestParam("id")int id, Model model) {
    	Notification notification = notificationService.getNotificationById(id);
        if (notification == null) {
            
        	throw new NoNotificationFoundException("Notification doesn't exists");
        }
        model.addAttribute("notification", notification);
        return "Notification-Details"; 
    }

    @GetMapping("/notification/all")
	public String getAllNotifications(Model model) throws NotificationListEmptyException{    
		List<Notification> notification = notificationService.getAllNotifications();
		model.addAttribute("AllNotifications", notification);
		model.addAttribute("notification", notification);
		return "allnotificationdetails";
	}
    
 // GET Mapping to display the delete category form
    @GetMapping("/deleteNotificationForm")
    public String showDeletenotificationForm() {
        return "NotificationRedirect";
    }

    @PostMapping("/deleteNotification")
    public String deleteNotification(@RequestParam int notificationId, Model model) {
        // Perform deletion operation
        boolean deleted = notificationService.deleteNotification(notificationId);
        // Check if deletion was successful
        if (deleted) {
            model.addAttribute("code", "DELETESUCCESS");
            model.addAttribute("message", "Notification deleted successfully");
        } else {
            model.addAttribute("code", "ENDPOINTFAILS");
            model.addAttribute("message", "Notification deletion failed because Notification doesn't exist");
        }
        // Return the view name for displaying the result
        return "deleteNotificationResult";
    }
    
    @GetMapping("/updateNotification")
    public String showUpdateNotificationForm(@RequestParam(name = "notificationID", required = false) Integer notificationID, Model model) {
        // Logic to retrieve category data if needed
        return "updateNotification";
    }

    @PostMapping("/updateNotification")
    public String updateNotificationFormSubmit(@RequestParam("notificationID") int notificationID, 
                                                @ModelAttribute("notification") Notification notification, 
                                                @RequestParam("userid") User userid, // Retrieve user ID from the form
                                                Model model) {
        
        // Set the retrieved User object in the Notification object
        notification.setUser(userid);
        
        boolean updated = notificationService.updateNotification(notificationID, notification);
        if (!updated) {
            throw new NotificationNotExistsException("Notification doesn't exist");
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "Notification updated successfully");
        model.addAttribute("response", response);
        return "notificationupdated"; // Redirect after processing form submission
    }
    @GetMapping("/notificationHome")
    public String showNotificationHomepage() {
        return "homeNotification";
    }

}

