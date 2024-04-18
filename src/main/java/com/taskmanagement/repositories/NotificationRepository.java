package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.taskmanagement.bean.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Integer>
{
	
	@Modifying
    @Query("DELETE FROM Notification n WHERE n.notificationID = :notificationId")
    void deleteById( int notificationId);

	@Modifying
	@Query("DELETE from Notification n where n.user.userID=:userId")
	void deleteByUserId(int userId);
	
}
