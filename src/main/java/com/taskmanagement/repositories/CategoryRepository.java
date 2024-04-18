package com.taskmanagement.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{
	    
	    boolean existsByCategoryID(int categoryId);   
	    
	    
	    @Query("SELECT c, COUNT(tc.task.taskID) " +
	            "FROM Category c LEFT JOIN TaskCategory tc ON c.categoryID = tc.category.categoryID " +
	            "GROUP BY c")
	    
	    
	     List<Object[]> findAllCategoriesWithTaskCount();
	    
	}

