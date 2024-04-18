package com.taskmanagement.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "Attachment")
public class Attachment {
    @Id
    @Column(name = "attachmentid")
    private int attachmentID;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "filepath")
    private String filePath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskid")
    private Task task;

	public int getAttachmentID() {
		return attachmentID;
	}

	public void setAttachmentID(int attachmentID) {
		this.attachmentID = attachmentID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Attachment(int attachmentID, String fileName, String filePath, Task task) {
		this.attachmentID = attachmentID;
		this.fileName = fileName;
		this.filePath = filePath;
		this.task = task;
	}

	public Attachment() {
	}

    
}

