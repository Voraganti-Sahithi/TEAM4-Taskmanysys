package com.taskmanagement.servicesimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Comment;
import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.exceptions.CommentAlreadyExistsException;
import com.taskmanagement.repositories.CommentRepository;
import com.taskmanagement.services.CommentService;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService
{
	@Autowired
	 private CommentRepository commentRepository;
        
	   //POSTING COMMENTS DATA
		@Override
		public ResponseEntity<Map<String, String>> createComment(Comment comment) 
		 {
			 Map<String, String> response = new HashMap<>();

		        if (commentRepository.existsByCommentID(comment.getCommentID())) {
		        	throw new CommentAlreadyExistsException("Comments already exit");
		        } else {
		            commentRepository.save(comment);
		            response.put("code", "POSTSUCCESS");
		            response.put("message", "Comments added successfully");
		            return new ResponseEntity<>(response, HttpStatus.CREATED);
		        }
		    }
		 
	    
	    //GETTING ALL COMMENTS
	    @Override
	    public List<Comment> getAllComment() {
	        return commentRepository.findAll();
	    }
		
		
		//GETTING COMMENT BY COMMENT-ID
	    @Override
	    public Comment getCommentById(int commentId) {
	        return commentRepository.findById(commentId).orElse(null);
	    }
	     
	    
	    //UPDATING COMMENT BY COMMENT-ID
	    @Override
	     public boolean updateComment(int commentId, Comment updatedComment) {
	         Optional<Comment> optionalComment = commentRepository.findById(commentId);
	         if (optionalComment.isPresent()) {
	        	 Comment existingComment = optionalComment.get();
	             existingComment.setText(updatedComment.getText());
	             existingComment.setCreatedAt(updatedComment.getCreatedAt() );
	             existingComment.setTask(updatedComment.getTask());
	             existingComment.setUser(updatedComment.getUser());
	             commentRepository.save(existingComment);
	             return true;
	         }
	         return false;
	 	}
	     
	     
	   //DELETE COMMENT BY COMMENT-ID
	    @Override
	    @Transactional
	    public boolean deleteComment(int commentId) {
	        if(commentRepository.existsByCommentID(commentId))
	        {
	            commentRepository.deleteCommentById(commentId);
	            return true;
	        }
	        return false;
	           
	    }
		
	    
		
		
}


