package com.taskmanagement.bean;

import jakarta.persistence.*;


@Entity
@Table(name = "Category")
public class Category {
    @Id
    @Column(name = "categoryid")
    private int categoryID;

    @Column(name = "categoryname")
    private String categoryName;

    

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public Category() {
		
	}

    
}

