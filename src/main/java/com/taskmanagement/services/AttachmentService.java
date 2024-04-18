package com.taskmanagement.services;

import java.util.List;

import com.taskmanagement.bean.Attachment;

public interface AttachmentService
{
	
	List<Attachment> getAllAttachments();

	Attachment getAttachmentById(int attachmentId);
	
	boolean updateAttachment(int attachmentId, Attachment updatedattachment);

	boolean createAttachment(Attachment attachment);
	
	boolean deleteAttachment(int attachmentId);

	

	

}
