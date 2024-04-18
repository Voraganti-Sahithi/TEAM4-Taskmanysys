package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskmanagement.bean.Attachment;
import com.taskmanagement.bean.Notification;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.User;
import com.taskmanagement.exceptions.AttachmentAlreadyExistsException;
import com.taskmanagement.exceptions.AttachmentDoesNotExistsException;
import com.taskmanagement.exceptions.AttachmentListEmptyException;
import com.taskmanagement.exceptions.NotificationAlreadyExistsException;
import com.taskmanagement.services.AttachmentService;

@Controller
@RequestMapping("/api1")
public class AttachmentThymeleafController {
	@Autowired
	AttachmentService attachmentService;
	
	//GET ATTACHEMNT BY ID
		@GetMapping("/attachment")
		public String Attachment() {
		    return "Get-Attachments";
		}
	
	
	@GetMapping("/attachment/{attachmentId}")
    public String getAttachmentById(@RequestParam("id")int id, Model model) {
    	
        Attachment attachment = attachmentService.getAttachmentById(id);
        if (attachment == null) {
            
        	throw new AttachmentDoesNotExistsException("Attachment doesn't exists");
        }
        model.addAttribute("attachment", attachment);
        return "Attachment-Details"; 
    }

    @GetMapping("/attachment/all")
	public String getAllAttachments(Model model) throws AttachmentListEmptyException{    
		List<Attachment> attachment = attachmentService.getAllAttachments();
		model.addAttribute("AllAttachments", attachment);
		model.addAttribute("attachment", attachment);
		return "allattachmentdetails";
	}
    
 // GET Mapping to display the delete attachment form
    @GetMapping("/deleteattachmentForm")
    public String showDeletenotificationForm() {
        return "redirect-attachment";
    }
    @PostMapping("/deleteAttachment")
    public String deleteAttachement(@RequestParam int attachmentId, Model model) {
        // Perform deletion operation
        boolean deleted = attachmentService.deleteAttachment(attachmentId);
        // Check if deletion was successful
        if (deleted) {
            model.addAttribute("code", "DELETESUCCESS");
            model.addAttribute("message", "Notification deleted successfully");
        } else {
            model.addAttribute("code", "ENDPOINTFAILS");
            model.addAttribute("message", "Attachment deletion failed because Attachemnt doesn't exist");
        }
        // Return the view name for displaying the result
        return "deleteAttachmentResult";
    }

    @GetMapping("/updateAttachmentForm")
    public String showUpdateAttachmentForm(@RequestParam(name = "attachmentID", required = false) Integer attachmentID, Model model) {
        // Logic to retrieve category data if needed
        return "updateAttachment";
    }
    @PostMapping("/updateAttachmentForm")
    public String updateAttachmentFormSubmit(@RequestParam("attachmentID") int attachmentID, @ModelAttribute("attachment") Attachment attachment, Model model) {
        boolean updated = attachmentService.updateAttachment(attachmentID, attachment);
	        if (updated==false) {
	        	throw new AttachmentDoesNotExistsException("Attachment doen't exist");
	        }
	        Map<String, String> response = new HashMap<>();
         response.put("code", "UPDATESUCCESS");
         response.put("message", "Attachment updated successfully");
         model.addAttribute("response", response);
         return "attachmentupdated"; // Redirect after processing form submission
    }
    @GetMapping("/attachmentHome")
    public String showAttachmentHomepage() {
        return "homeAttachment";
    }
    
  //CREATE NOTIFICATION
  	@PostMapping("/create/newattachment")
      public String createAttachment(@ModelAttribute("attachment") Attachment attachment, Model model) {
  		 boolean created = attachmentService.createAttachment(attachment);
	        if (created==false) {
	        	throw new AttachmentAlreadyExistsException("Attachment already exists");
	        }
          model.addAttribute("code", "POSTSUCCESS");
          model.addAttribute("message", "Attachment added successfully");
          return "attachmentcreated"; // Return the name of the success view
      }

      @GetMapping("/create/attachment")
      public String showAttachmentForm(Model model) throws AttachmentAlreadyExistsException {
          model.addAttribute("attachment", new Attachment());
          return "post-Attachment"; // Return the name of the form view
      }
    
    }



