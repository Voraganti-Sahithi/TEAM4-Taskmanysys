package com.taskmanagement.servicesimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.bean.Category;
import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.repositories.CategoryRepository;
import com.taskmanagement.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	 private CategoryRepository categoryRepository;
  
	//POSTTING THE CATEGORY DATA
	 @Override
	 public ResponseEntity<Map<String, String>> createCategory(Category category) 
	 {
		 Map<String, String> response = new HashMap<>();

	        if (categoryRepository.existsByCategoryID(category.getCategoryID())) {
	        	throw new CategoryAlreadyExistsException("Category already exit");
	        } else {
	            categoryRepository.save(category);
	            response.put("code", "POSTSUCCESS");
	            response.put("message", "Category added successfully");
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        }
	    }
	
	 
	//GETTING ALL CATEGORIES
	 @Override
	    public List<Category> getAllCategory() {
	        return categoryRepository.findAll();
	    }
    
	 
	 //GETTING CATEGORY BY CATEGORY-ID
	 @Override
	    public Category getCategoryById(int categoryId) {
	        return categoryRepository.findById(categoryId).orElse(null);
	    }
     
     
     // GETTING TASK - COUNT
     @Override
     public List<Object[]> getCategoriesWithTaskCount() {
         return categoryRepository.findAllCategoriesWithTaskCount();
     }

     
     
     //UPDATING CATEGORY BY CATEGORY-ID  
     @Override
     public boolean updateCategory(int categoryId, Category updatedCategory) {
         Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
         if (optionalCategory.isPresent()) {
             Category existingCategory = optionalCategory.get();
             existingCategory.setCategoryName(updatedCategory.getCategoryName());
             categoryRepository.save(existingCategory);
             return true;
         }
         return false;
 	 }
     
     
     //DELETE CATEGORY BY CATEGORY-ID
     @Override
     public boolean deleteCategory(int categoryId) {
         Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
         if (optionalCategory.isPresent()) {
             categoryRepository.deleteById(categoryId);
             return true;
         }
         return false;
     }
     
}


