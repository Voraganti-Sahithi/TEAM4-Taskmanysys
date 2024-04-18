package com.taskmanagement.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.Task;

import jakarta.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> 
{
    List<Task> findByPriorityAndStatus(String priority, String status);
   
    List<Task> findByUserUserIDAndStatus(int userId, String status);
    
    List<Task> findByDueDateBeforeAndStatus(LocalDate currentDate, String status);
   
    @Query("SELECT t FROM Task t JOIN TaskCategory tc ON t.taskID = tc.task.taskID WHERE tc.category.categoryID = :categoryId")
    List<Task> findByCategoryId(int categoryId);
    
    List<Task> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Modifying
	@Query("DELETE from Task t where t.taskID=:taskId")
	void deleteByTaskId(int taskId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Task t WHERE t.project.projectID=:projectId")
	void deleteByProjectId(int projectId);
	
	@Modifying
	@Query("DELETE from Task t where t.user.userID=:userId")
	void deleteByUserId(int userId);

}
