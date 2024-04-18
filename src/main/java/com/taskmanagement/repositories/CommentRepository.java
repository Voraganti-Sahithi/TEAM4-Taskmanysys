package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.Comment;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	boolean existsByCommentID(int commentId);
	 
	
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.commentID = :commentId")
    void deleteCommentById( int commentId);

    
    @Modifying
    @Query("DELETE from Comment c where c.task.taskID=:taskId")
     void deleteByTaskId(int taskId);
    
    
	@Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.task.project.projectID = :projectId")
    void deleteByProjectId(int projectId);
	
	
	@Modifying
	@Query("DELETE from Comment c where c.user.userID=:userId")
	void deleteByUserId(int userId);
}
