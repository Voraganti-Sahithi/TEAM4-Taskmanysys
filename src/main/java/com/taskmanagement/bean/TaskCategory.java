package com.taskmanagement.bean;

import jakarta.persistence.*;


@Entity
@Table(name = "taskcategory")
@IdClass(TaskCategoryId.class)
public class TaskCategory {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TaskID")
    private Task task;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CategoryID")
    private Category category;

	public TaskCategory(Task task, Category category) {
		super();
		this.task = task;
		this.category = category;
	}

	public TaskCategory() {
		super();
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

