package com.taskmanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.taskmanagement.bean.Category;
import com.taskmanagement.exceptions.CategoryDoesNotExistExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CategoryListEmptyException;
import com.taskmanagement.exceptions.CategoryListNotExistsException;
import com.taskmanagement.exceptions.CategoryNotExistsException;
import com.taskmanagement.services.CategoryService;

@RestController
@RequestMapping("/api/categories")

public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;

    //POSTING CATEGORY DATA
	@PostMapping("/post")
	 public ResponseEntity<Map<String, String>> createCategory(@RequestBody Category category)
	 {
		return categoryService.createCategory(category);
	 }
	
    
	//GETTING ALL CATEGORIES
	@GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> category = categoryService.getAllCategory();
        if(category.isEmpty())
        	throw new CategoryListEmptyException("Category list is empty");
        return ResponseEntity.ok(category);
    }
	
	
	 //GETTING CATEGORIES BY CATEGORY-ID
	 @GetMapping("/{categoryId}")
	    public ResponseEntity<Category> getUserById(@PathVariable int categoryId) {
	        Category category = categoryService.getCategoryById(categoryId);
	        if(category==null)
	        	throw new CategoryNotExistsException("Category doesn't exist");
	        return ResponseEntity.ok(category);   
	    }
	 
	 
	 //GETTING TASK-COUNT
	 @GetMapping("/task-count")
	 public ResponseEntity<?> getCategoriesWithTaskCount() {
	        List<Object[]> categoriesWithTaskCount = categoryService.getCategoriesWithTaskCount();
	        if(categoriesWithTaskCount.isEmpty())
	        	throw new CategoryListNotExistsException("Category doesn't exist exist");
	       
	        List<Map<String, Object>> processedList = categoriesWithTaskCount.stream()
	                .map(array -> {
	                    Category category = (Category) array[0];
	                    Long taskCount = (Long) array[1];
	                    return Map.of("category", category, "taskCount", taskCount);
	                })
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(processedList);
	    }	 
	 
	 
	 //UPDATING CATEGORY BY CATEGORY-ID
	 @PutMapping("/update/{categoryId}")
	 public ResponseEntity<Map<String, String>> updateCategory(@PathVariable int categoryId, @RequestBody Category updatedCategory) {
	        boolean updated = categoryService.updateCategory(categoryId, updatedCategory);
	        if (updated==false) {
	        	throw new CategoryDoesNotExistsException("Category doen't exist");
	            
	        }
	        Map<String, String> response = new HashMap<>();
            response.put("code", "UPDATESUCCESS");
            response.put("message", "Category updated successfully");
            return ResponseEntity.ok(response);
	   }	 
	 
	 
	 //DELETE CATEGORY BY CATEGORY-ID
	 @DeleteMapping("/{categoryId}")
	 public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable int categoryId)  {
	        boolean deleted = categoryService.deleteCategory(categoryId);
	        if (deleted) {
	            Map<String, String> response = new HashMap<>();
	            response.put("code", "DELETESUCCESS");
	            response.put("message", "Category deleted successfully");
	            return ResponseEntity.ok(response);
	        } else {
	            throw new CategoryDoesNotExistExistsException("Category doesn't exist exist");
	        }
	    }

}


