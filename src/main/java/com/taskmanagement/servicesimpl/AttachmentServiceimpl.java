package com.taskmanagement.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Attachment;
import com.taskmanagement.repositories.AttachmentRepository;
import com.taskmanagement.services.AttachmentService;

import jakarta.transaction.Transactional;

@Service
public class AttachmentServiceimpl implements AttachmentService {

	@Autowired
    private AttachmentRepository attachmentRepository;
	@Override
	
	//INSERT DATA INTO ATTACHMENT TABLE
	public boolean createAttachment(Attachment attachment) {
		if (attachmentRepository.existsById(attachment.getAttachmentID())) {
            return false; // Attachment already exists
        } else {
        	attachmentRepository.save(attachment);
            return true; // Attachment added successfully
        }
		
	}

	// GET ALL DATA IN ATTACHMENT TABLE
	@Override
	public List<Attachment> getAllAttachments() {
		
		return attachmentRepository.findAll();
	}
	
	
	
	// GET SPECIFIC DATA BY ATACHMENT ID
	@Override
	public Attachment getAttachmentById(int attachmentId) {
		
		return attachmentRepository.findById(attachmentId).orElse(null);
	}
	
	
	// UPDATE DATA BY ATTACHMENT ID
	@Override
	public boolean updateAttachment(int attachmentId, Attachment updatedattachment) {
		Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
		if(optionalAttachment.isPresent()) {
			Attachment existingAttachment = optionalAttachment.get();
			if(updatedattachment.getFileName() != null && updatedattachment.getFilePath() != null) {
			existingAttachment.setFileName(updatedattachment.getFileName());
            existingAttachment.setFilePath(updatedattachment.getFilePath());
			}
            attachmentRepository.save(existingAttachment);
            return true;
		}
		return false;
	}
	
	//DELETE DATA BY ATTACHMENT ID
	@Override
	@Transactional
	public boolean deleteAttachment(int attachmentId) {
		Optional<Attachment> optionalattachment = attachmentRepository.findById(attachmentId);
		if(optionalattachment.isPresent()) {
			attachmentRepository.deleteById(attachmentId);
			return true;
		}
		return false;
	}
	

}
