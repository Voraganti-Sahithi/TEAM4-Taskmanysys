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

import com.taskmanagement.bean.Attachment;
import com.taskmanagement.exceptions.AttachmentAlreadyExistsException;
import com.taskmanagement.exceptions.AttachmentDoesNotExistExistsException;
import com.taskmanagement.exceptions.AttachmentDoesNotExistsException;
import com.taskmanagement.exceptions.AttachmentListEmptyException;
import com.taskmanagement.exceptions.NoAttachmentFoundException;
import com.taskmanagement.services.AttachmentService;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
	 @Autowired
	 private AttachmentService attachmentservice;
	 
	 //INSERT DATA INTO ATTACHMENT TABLE
	 @PostMapping("/post")
	    public ResponseEntity<String> createAttachment(@RequestBody Attachment attachment) {
	        boolean created = attachmentservice.createAttachment(attachment);
	        if (created==false) {
	        	throw new AttachmentAlreadyExistsException("Attachment already exists");
	        }
	            return ResponseEntity.status(HttpStatus.CREATED).body("{\"code\": \"POSTSUCCESS\", \"message\": \"Attachment added successfully\"}");
	        
	    }
	 

    // GET ALL DATA IN ATTACHMENT TABLE
    @GetMapping("/all")
    public ResponseEntity<Object> getAllAttachments() {
        List<Attachment> attachments = attachmentservice.getAllAttachments();
        if (attachments.isEmpty()) {
        	throw new AttachmentListEmptyException("Attachment list is empty");
        	
        }
        return ResponseEntity.ok(attachments);
     
    }
    
    
    // GET SPECIFIC DATA BY ATACHMENT ID
    @GetMapping("/{attachmentId}")
    public ResponseEntity<Object> getAttachmentById(@PathVariable int attachmentId) {
        Attachment attachment = attachmentservice.getAttachmentById(attachmentId);
        if (attachment == null) {
        	throw new NoAttachmentFoundException("Attachment doesn't exist");
            
        } 
            return ResponseEntity.ok(attachment);
    }
        
    
    
    // UPDATE DATA BY ATTACHMENT ID
    @PutMapping("/update/{attachmentId}")
    public ResponseEntity<Object> updateAttachment(@PathVariable int attachmentId , @RequestBody Attachment updatedattachment){
    	boolean isupdate=attachmentservice.updateAttachment(attachmentId ,updatedattachment);
    	if(isupdate==false) {
    		throw new AttachmentDoesNotExistsException("Attachment doesn't exists");
    	}
    	else {
        	Map<String ,String> response=new HashMap<>();
            response.put(  "code","UPDATESUCCESS");
            response.put("message","Attachment updated successfully");
            return ResponseEntity.ok(response);
           
        }
    }
    
    
    //DELETE DATA BY ATTACHMENT ID
    @DeleteMapping("/delete/{attachmentId}")
    public ResponseEntity<Map<String ,String>> deleteAttachment(@PathVariable int attachmentId){
    	boolean isdelete=attachmentservice.deleteAttachment(attachmentId);
    	if(isdelete) {
    		Map<String ,String> response=new HashMap<>();
            response.put(  "code","DLTSUCCESS");
            response.put("message","Attachment delete successfully");
            return ResponseEntity.ok(response);
    		
    	}
    	else {
    		throw new AttachmentDoesNotExistExistsException("Attachment Does not exist");
    	}
		
    	
    }
    }
                                      


