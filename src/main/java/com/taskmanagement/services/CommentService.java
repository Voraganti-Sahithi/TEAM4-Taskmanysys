package com.taskmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Comment;

public interface CommentService 
{
	   ResponseEntity<Map<String, String>> createComment(Comment comment);
	   
	   
	   List<Comment> getAllComment();
	   
	   
	   Comment getCommentById(int commentId);
		
	   
       boolean updateComment(int commentId, Comment updatedComment);
	
       
	   boolean deleteComment(int commentId);
}


