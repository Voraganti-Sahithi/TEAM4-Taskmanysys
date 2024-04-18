package com.taskmanagement.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.TaskCategory;

import jakarta.transaction.Transactional;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> 
{
	
    List<TaskCategory> findByTaskTaskID(int taskId);
    
    List<TaskCategory> findByCategoryCategoryID(int categoryId);
    
    TaskCategory findByTaskAndCategory(Task task, Category category);
   
    @Modifying
	@Query("DELETE from TaskCategory tc where tc.task.taskID=:taskId")
	void deleteByTaskId(int taskId);

	@Transactional
    @Modifying
    @Query("DELETE FROM TaskCategory tc WHERE tc.task.project.projectID = :projectId")
    void deleteByProjectId(int projectId);
	
	
}

