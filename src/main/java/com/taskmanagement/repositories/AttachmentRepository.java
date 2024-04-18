package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.taskmanagement.bean.Attachment;

import jakarta.transaction.Transactional;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer>{

	
	@Modifying
    @Query("DELETE FROM Attachment a WHERE a.attachmentID = :attachmentId")
    void deleteById( int attachmentId);
	
    @Modifying
	@Query("DELETE from Attachment a where a.task.taskID=:taskId")
	void deleteByTaskId(int taskId);
      
  	@Transactional
      @Modifying
      @Query("DELETE FROM Attachment a WHERE a.task.project.projectID = :projectId")
      void deleteByProjectId(int projectId);
	
}
