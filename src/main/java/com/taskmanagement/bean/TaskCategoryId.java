package com.taskmanagement.bean;

import java.io.Serializable;

public class TaskCategoryId implements Serializable {
	
	private Task task;
    private Category category;
    

	public TaskCategoryId() {
		super();
	}
    
    public TaskCategoryId(Task task, Category category) {
		super();
		this.task = task;
		this.category = category;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	

   
}

