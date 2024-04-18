package com.taskmanagement.services;

import java.util.List;

import com.taskmanagement.bean.Notification;

public interface NotificationService {

	boolean createNotification(Notification notification);

	List<Notification> getAllNotifications();

	Notification getNotificationById(int notificationId);

	boolean updateNotification(int notificationId, Notification updatedNotification);

	boolean deleteNotification(int notificationId);

	
	}
