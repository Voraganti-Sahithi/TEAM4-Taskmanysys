package com.taskmanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Comment;
import com.taskmanagement.exceptions.CategoryDoesNotExistExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CategoryListEmptyException;
import com.taskmanagement.exceptions.CategoryNotExistsException;
import com.taskmanagement.exceptions.CommentDoesNotExistExistsException;
import com.taskmanagement.exceptions.CommentDoesNotExistsException;
import com.taskmanagement.exceptions.CommentListEmptyException;
import com.taskmanagement.exceptions.CommentNotExistsException;
import com.taskmanagement.services.CommentService;

@RestController
@RequestMapping("/api/comments")

public class CommentController 
{
	@Autowired
    private CommentService commentService;
    
	//POSTING THE COMMENTS DATA
    @PostMapping("/post")
	 public ResponseEntity<Map<String, String>> createComment(@RequestBody Comment comment)
	 {
		return commentService.createComment(comment);
	 }
    
    
    //GETTING ALL COMMENTS
    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment() {
        List<Comment> comment = commentService.getAllComment();
        if(comment.isEmpty())
        	throw new CommentListEmptyException("Comment list is empty");
        return ResponseEntity.ok(comment);
    }
    
    
    //GETTING COMMENTS BY COMMENT-ID
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable int commentId) {
    	Comment comment = commentService.getCommentById(commentId);
    	if(comment==null)
        	throw new CommentNotExistsException("Comment doesn't exist");
        return ResponseEntity.ok(comment);
    }
    
    
    //UPDATING COMMENT BY COMMENT-ID
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Map<String, String>> updateComment(@PathVariable int commentId, @RequestBody Comment updatedComment) {
        boolean updated = commentService.updateComment(commentId, updatedComment);
        if (updated==false) {
        	throw new CommentDoesNotExistsException("Comment doen't exist");
            
        }
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "Comment updated successfully");
        return ResponseEntity.ok(response);
    }
    
    
    //DELETE COMMENT BY COMMENT-ID   
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable int commentId) {
        boolean deleted = commentService.deleteComment(commentId);
        if (deleted) {
        	Map<String, String> response = new HashMap<>();
            response.put("code", "DELETESUCCESS");
            response.put("message", "Comment deleted successfully");
            return ResponseEntity.ok(response);
            
        } else {
        	throw new CategoryDoesNotExistExistsException("Comment doesn't exist exist");
        }
    }
    
   
    
}


