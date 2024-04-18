package com.taskmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.bean.Category;

public interface CategoryService 
{
	 ResponseEntity<Map<String, String>> createCategory(Category category);
	
	
	 List<Category> getAllCategory();
	
	
	 Category getCategoryById(int categoryId);
	
	
	 List<Object[]> getCategoriesWithTaskCount();
	
	
	 boolean updateCategory(int categoryId, Category updatedCategory);
	
	
	 boolean deleteCategory(int categoryId);
}


