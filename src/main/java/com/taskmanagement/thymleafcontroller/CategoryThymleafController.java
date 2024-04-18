package com.taskmanagement.thymleafcontroller;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmanagement.bean.Category;
import com.taskmanagement.exceptions.CategoryAlreadyExistsException;
import com.taskmanagement.exceptions.CategoryDoesNotExistsException;
import com.taskmanagement.exceptions.CategoryListEmptyException;
import com.taskmanagement.exceptions.CategoryListNotExistsException;
import com.taskmanagement.exceptions.CategoryNotExistsException;
import com.taskmanagement.services.CategoryService;

@Controller
@RequestMapping("/api1")
public class CategoryThymleafController 
{
	@Autowired
	CategoryService categoryService;
	
   //METHOD FOR DISPLAYING THE FORM
	@GetMapping("/category")
	public String showdetailsforcategoryid()
	{
		return "category-form";
	}
	
	
   //POSTING CATEGORY DATA
	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> createCategory(@ModelAttribute("category") Category category) { 
		return categoryService.createCategory(category);
	    
	 }
		
    @GetMapping("/create")
    public String showCategoryForm(Model model) throws CategoryAlreadyExistsException {
		 model.addAttribute("category", new Category());
		 return "postcategory";
     }
		
		
   //GETTING ALL CATEGORIES
	@GetMapping("/categories")
	public String getAllCategories(Model model) throws CategoryListEmptyException
	{
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("AllCategories", category);
		model.addAttribute("category", category);
		return "allcategorydetails";
	}
	
	
   //GETTING CATEGORY BY CATEGORY-ID
	//http://localhost:9999/api/category/categoryid?id={categoryId}
	@GetMapping("/category/categoryid")
	public String retrieveByCategoryId(@RequestParam("id") int id, Model model)
	{
		Category category = categoryService.getCategoryById(id);
		if(category == null)
		{
			throw new CategoryNotExistsException("Category doesn't exist");
		}
		model.addAttribute("category", category);
		return "categorydetails";
	}
	
	
   //GETTING TASK-COUNT WITH CATEGORYID
	@GetMapping("/task-count")
	public String getCategoriesWithTaskCount(Model model) throws CategoryListNotExistsException
	{
		List<Object[]> category = categoryService.getCategoriesWithTaskCount();
		model.addAttribute("AllTaskCounts", category);
		model.addAttribute("AllCategories", category);
		model.addAttribute("category", category);
		return "categorieswithtaskcount";		
	}
	
	//UPDATING CATEGORY BT CATEGORY-ID
    @GetMapping("/updateCategoryForm")
    public String showUpdateCategoryForm(@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
        return "updateCategory";
    }
    
	@PostMapping("/updateCategory")
    public String updateCategoryFormSubmit(@RequestParam("categoryId") int categoryId, @ModelAttribute("Category") Category category, Model model) {
        boolean updated = categoryService.updateCategory(categoryId, category);
	        if (updated==false) {
	        	throw new CategoryDoesNotExistsException("Category doen't exist");
	            
	        }
	        Map<String, String> response = new HashMap<>();
         response.put("code", "UPDATESUCCESS");
         response.put("message", "Category updated successfully");
         model.addAttribute("response", response);
         return "update-success"; // Redirect after processing form submission
    }
	
	
   //GET MAPPING TO DISPLAY THE DELETE CATEGORY FORM
    @GetMapping("/deleteCategoryForm")
    public String showDeleteCategoryForm() {
        return "redirect";
    }
    
    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam int categoryId, Model model) 
    { 
        boolean deleted = categoryService.deleteCategory(categoryId);
        
        if (deleted) {
            model.addAttribute("code", "DELETESUCCESS");
            model.addAttribute("message", "Category deleted successfully");
        } else {
            model.addAttribute("code", "ENDPOINTFAILS");
            model.addAttribute("message", "Category deletion failed because category doesn't exist");
        }
        
        return "deleteCategoryResult";
    }
    
   
    
    
   //LOGIN PAGE 
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    
    @GetMapping("/categoryHome")
    public String showCategoryHomePage() {
    	return "homeCategory";
    }
  
}


