package com.taskmanagement.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.taskmanagement.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u JOIN Task t ON u = t.user GROUP BY u.userID ORDER BY COUNT(t.taskID) DESC LIMIT 10")
    List<User> findUsersWithMostTasks();
    
    @Query(value = "SELECT u FROM User u INNER JOIN Task t ON u = t.user WHERE t.status = 'Completed'")
    List<User> findUsersWithCompletedTasks();
	
	List<User> findByFullNameIgnoreCase(String name);
	 
    @Modifying
	@Query("DELETE FROM User u WHERE u.userID = :userId")
	void deleteById( int userId);
	
	List<User> findByEmailContaining(String string);
		
	User findByusernameAndPassword(String string,String password);
		
		
}
	    


