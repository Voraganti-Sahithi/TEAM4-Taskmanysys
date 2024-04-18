 package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.Category;
import com.taskmanagement.bean.Comment;
import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CommentAlreadyExistsException;
import com.taskmanagement.exceptions.CommentDoesNotExistsException;
import com.taskmanagement.exceptions.CommentListEmptyException;
import com.taskmanagement.exceptions.CommentNotExistsException;
import com.taskmanagement.services.CommentService;

@Controller
@RequestMapping("/api1")
public class CommentThymleafController 
{
	@Autowired
	CommentService commentService;
	
   //METHOD FOR DISPLAYING THE FORM
	@GetMapping("/comment")
	public String showdetailsforcommentid()
	{
		return "comment-form";
	}
	
	
   //POSTING COMMENT DATA
	@PostMapping("comment/create")
    public ResponseEntity<Map<String, String>> createComment(@ModelAttribute("comment") Comment comment) {
        return commentService.createComment(comment);
    }
	
	@GetMapping("comment/create")
	public String showCommentForm(Model model) throws CommentAlreadyExistsException {
	    model.addAttribute("comment", new Comment());
	    return "postcomment";
	}
	
	
   //GETTING ALL CATEGORIES
	@GetMapping("/comments")
	public String getAllComments(Model model) throws CommentListEmptyException
	{
		List<Comment> comment = commentService.getAllComment();
		model.addAttribute("AllComments", comment);
		model.addAttribute("comment", comment);
		return "allcommentdetails";
	}
	
	
   //GETTING COMMENT BY COMMENT-ID
	//http://localhost:9999/api/comment/commentid?id=3
	@GetMapping("/comment/commentid")
	public String retrieveByCommentId(@RequestParam("id") int id, Model model)
	{
		Comment comment = commentService.getCommentById(id);
		if(comment == null)
		{
			throw new CommentNotExistsException("Comment doesn't exist");
		}
		model.addAttribute("comment", comment);
		return "commentdetails";
	}
	
	
	//UPDATING CATEGORY BT CATEGORY-ID
    @GetMapping("/updateCommentForm")
    public String showUpdateCommentForm(@RequestParam(name = "commentId", required = false) Integer commentId, Model model) {
        return "updateComment";
    }
    
	@PostMapping("/updateComment")
    public String updateCommentFormSubmit(@RequestParam("commentId") int commentId, @ModelAttribute("Comment") Comment comment, Model model) {
		boolean updated = commentService.updateComment(commentId, comment);
        if (updated==false) {
        	throw new CommentDoesNotExistsException("Comment doen't exist");
            
        }
        Map<String, String> response = new HashMap<>();
        response.put("code", "UPDATESUCCESS");
        response.put("message", "Comment updated successfully");
        model.addAttribute("response", response);
        return "update-success";
    }
	
	
   //GET MAPPING TO DISPLAY THE DELETE COMMENT FORM
    @GetMapping("/deleteCommentForm")
    public String showDeleteCategoryForm() {
        return "redirectcomment";
    }
    
    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam int commentId, Model model) 
    {
        boolean deleted = commentService.deleteComment(commentId);
        
        if (deleted) {
            model.addAttribute("code", "DELETESUCCESS");
            model.addAttribute("message", "Comment deleted successfully");
        } else {
            model.addAttribute("code", "ENDPOINTFAILS");
            model.addAttribute("message", "Comment deletion failed because category doesn't exist");
        }
        return "deleteCommentResult";
    }
    @GetMapping("/commentHome")
    public String showCommentHomePage() {
    	return "homeComment";
    }
}
