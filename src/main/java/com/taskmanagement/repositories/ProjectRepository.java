package com.taskmanagement.repositories;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.bean.Project;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	 List<Project> findAllByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);
	 
	 List<Project> findAllByStartDateBetween(LocalDate startDate, LocalDate endDate);

	 @Query("SELECT p FROM Project p JOIN UserRoles ur ON p.user = ur.user WHERE ur.userRole.roleName = :roleName")
	 List<Project> findByUserRoleRoleName(String roleName);
	 
	 
	 @Query("SELECT p FROM Project p WHERE EXISTS (SELECT t FROM Task t WHERE t.project = p AND t.status = :status)")
	 List<Project> findByStatus(String status);
	 
	 @Query("SELECT DISTINCT p FROM Project p WHERE EXISTS " +
	           "(SELECT t FROM Task t WHERE t.project = p AND t.priority = 'HIGH')")
	    List<Project> findProjectsWithHighPriorityTasks();
	 
	 
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM Project p WHERE p.projectID = :projectId")
	 void deleteByProjectID(int projectId);
	 
	 @Modifying
	 @Query("DELETE from Project p where p.user.userID=:userId")
	 void deleteByUserId(int userId);
}


