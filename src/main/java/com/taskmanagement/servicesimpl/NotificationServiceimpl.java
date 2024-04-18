package com.taskmanagement.servicesimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskmanagement.bean.Notification;
import com.taskmanagement.repositories.NotificationRepository;
import com.taskmanagement.services.NotificationService;

@Service
public class NotificationServiceimpl implements NotificationService{
	@Autowired
	private NotificationRepository notificationrepository;
	public NotificationServiceimpl(NotificationRepository notificationrepository) {
		super();
		this.notificationrepository = notificationrepository;
	}
	
	//INSERT DATA INTO NOTIFICION TABLE
	 @Override
	    public boolean createNotification(Notification notification) {
	        if (notificationrepository.existsById(notification.getNotificationID())) {
	            return false; // Notification already exists
	        } else {
	            notificationrepository.save(notification);
	            return true; // Notification added successfully
	        }
	    }
	

	

	// GET ALL DATA FROM NOTIFICAION TABLE
	@Override
	public List<Notification> getAllNotifications() {
		
		return notificationrepository.findAll();
	}
	
	
	
	

	// GET DATA BY NOTIFICATIONID FROM NOTIFICATION TABLE
	@Override
	public Notification getNotificationById(int notificationId) {
		
		return notificationrepository.findById(notificationId).orElse(null);
	}
	
	
	
	// UPDATE DATA BY USING NOTIFICATIONID FROM NOTIFICATION TABLE
	@Override
	public boolean updateNotification(int notificationId, Notification updatedNotification) {
		Optional<Notification> optionalNotification =notificationrepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
        	Notification existingNotification = optionalNotification.get();
            existingNotification.setText(updatedNotification.getText());
            existingNotification.setCreatedAt(updatedNotification.getCreatedAt());
            existingNotification.setUser(updatedNotification.getUser());
            notificationrepository.save(existingNotification);
            return true;
        }
		return false;
	}	
	
	
	// DELETE DATA BY USING NOTIFICATIONID FROM NOTIFICATION TABLE
	@Override
	public boolean deleteNotification(int notificationId) {
		Optional<Notification> optionalNotification =notificationrepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
        	notificationrepository.deleteById(notificationId);
        	return true;
        	
        }
	 
		return false;
		
		
	}
}
	


	



	

	


