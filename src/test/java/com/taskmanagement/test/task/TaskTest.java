package com.taskmanagement.test.task;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.time.LocalDate;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.taskmanagement.bean.Project;
import com.taskmanagement.bean.Task;
import com.taskmanagement.bean.User;
import com.taskmanagement.repositories.TaskRepository;
import com.taskmanagement.servicesimpl.TaskServiceImpl;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TaskTest {
	
	@Mock
    private TaskRepository taskRepository;
 
    @InjectMocks
    private TaskServiceImpl taskService;
 
    private Task existingTask;
    private Task updatedTask;
 
    @BeforeEach
    void setUp() {
    	
    	User existingUser = new User(1,"john.doe@email.com","John Doe",	"password123","john_doe");
    	
        Project existingProject = new Project();
        existingProject.setProjectID(1);
        existingProject.setDescription("Description for Project One");
        existingProject.setEndDate(LocalDate.of(2022, 02, 01));
        existingProject.setProjectName("Project One");
        existingProject.setStartDate(LocalDate.of(2022, 01, 01));
        existingProject.setUser(existingUser);
        
        existingTask = new Task();
        existingTask.setTaskID(1);
        existingTask.setTaskName("Task One");
        existingTask.setDescription("Description for Task One");
        existingTask.setDueDate(LocalDate.of(2022, 01, 10));
        existingTask.setPriority("High");
        existingTask.setStatus("In Progress");
        existingTask.setProject(existingProject);
        existingTask.setUser(existingUser);
        
        User updatedUser = new User(2,"jane.smith@email.com","Jane Smith","pass456","jane_smith");
        
 
        updatedTask = new Task();
        updatedTask.setTaskID(2);
        updatedTask.setTaskName("Task Two");
        updatedTask.setDescription("Updated Task Description");
        updatedTask.setDueDate(LocalDate.of(2022, 02, 15));
        updatedTask.setPriority("Medium");
        updatedTask.setStatus("Pending");
        updatedTask.setProject(existingProject);
        updatedTask.setUser(updatedUser);
    }
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testRetrieveOverdueTasks() throws JSONException
	{
		String overdueUrl = "/api/tasks/overdue";
		String expectedJson = """
				[
				    {
				        "taskID": 2,
				        "taskName": "Task Two",
				        "description": "Description for Task Two",
				        "dueDate": "2022-02-15",
				        "priority": "Medium",
				        "status": "Pending",
				        "project": {
				            "projectID": 1,
				            "projectName": "Project One",
				            "description": "Description for Project One",
				            "startDate": "2022-01-01",
				            "endDate": "2022-02-01",
				            "user": {
				                "userID": 1,
				                "username": "john_doe",
				                "password": "password123",
				                "email": "john.doe@email.com",
				                "fullName": "John Doe"
				            }
				        },
				        "user": {
				            "userID": 2,
				            "username": "jane_smith",
				            "password": "pass456",
				            "email": "jane.smith@email.com",
				            "fullName": "Jane Smith"
				        }
				    },
				    {
				        "taskID": 4,
				        "taskName": "Define Product Features",
				        "description": "Create a list of features for the new product",
				        "dueDate": "2022-02-18",
				        "priority": "High",
				        "status": "Pending",
				        "project": {
				            "projectID": 4,
				            "projectName": "New Product Launch",
				            "description": "Launching our latest product",
				            "startDate": "2022-02-15",
				            "endDate": "2022-04-30",
				            "user": {
				                "userID": 4,
				                "username": "emily_jackson",
				                "password": "my_pass",
				                "email": "emily.jackson@email.com",
				                "fullName": "Emily Jackson"
				            }
				        },
				        "user": {
				            "userID": 4,
				            "username": "emily_jackson",
				            "password": "my_pass",
				            "email": "emily.jackson@email.com",
				            "fullName": "Emily Jackson"
				        }
				    },
				    {
				        "taskID": 9,
				        "taskName": "Sales Training Program",
				        "description": "Training program for the sales team",
				        "dueDate": "2022-04-15",
				        "priority": "Medium",
				        "status": "Pending",
				        "project": {
				            "projectID": 9,
				            "projectName": "Sales Training Program",
				            "description": "Training program for the sales team",
				            "startDate": "2022-04-15",
				            "endDate": "2022-06-15",
				            "user": {
				                "userID": 9,
				                "username": "peter_anderson",
				                "password": "peterpass",
				                "email": "peter.anderson@email.com",
				                "fullName": "Peter Anderson"
				            }
				        },
				        "user": {
				            "userID": 9,
				            "username": "peter_anderson",
				            "password": "peterpass",
				            "email": "peter.anderson@email.com",
				            "fullName": "Peter Anderson"
				        }
				    },
				    {
				        "taskID": 10,
				        "taskName": "Internal Documentation Revamp",
				        "description": "Updating internal documentation and knowledge base",
				        "dueDate": "2022-06-01",
				        "priority": "Low",
				        "status": "Pending",
				        "project": {
				            "projectID": 10,
				            "projectName": "Internal Documentation Revamp",
				            "description": "Updating internal documentation and knowledge base",
				            "startDate": "2022-06-01",
				            "endDate": "2022-07-15",
				            "user": {
				                "userID": 10,
				                "username": "natalie_brown",
				                "password": "natalie456",
				                "email": "natalie.brown@email.com",
				                "fullName": "Natalie Brown"
				            }
				        },
				        "user": {
				            "userID": 10,
				            "username": "natalie_brown",
				            "password": "natalie456",
				            "email": "natalie.brown@email.com",
				            "fullName": "Natalie Brown"
				        }
				    }
				]
				""";
		ResponseEntity<String> response = template.getForEntity(overdueUrl, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), true);
	}
	@Test
	public void testRetrieveTasksBasedonPriorityAndStatus() throws JSONException
	{
		String url = "/api/tasks/priority/High/status/In progressIn progressIn progressIn";
		String expectedJson = """
				[
				    {
				        "taskID": 1
				    },
				    {
				        "taskID": 6
				    },
				    {
				        "taskID": 7
				    }
				]
				""";
		
		assertFalse(Validation.extractString(url));
	}
	
	@Test
	public void testRetrieveTasksBasedonUserAndStatus() throws JSONException
	{
		String url = "/api/tasks/user/8/status/In Progress";
		String expectedJson = """
				[
                    {
				    	"taskID": 8
					}
				]
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
	
	@Test
	public void testRetrieveTasksBasedonCategoryId() throws JSONException
	{
		String url = "/api/tasks/category/4";
		String expectedJson = """
				[
					{
						"taskID": 5
					}
				]
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
	
	@Test
	public void testRetrieveTasksBasedonDuesoon() throws JSONException
	{
		String url = "/api/tasks/due-soon";
		String expectedJson = """
				{
				    "code": "GETALLFAILS",
				    "message": "Task list is empty"
				}
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
	
	@Test
	public void testDeleteByTaskId() throws JSONException
	{
		String url = "/api/tasks/16";
		String expectedJson = """
				{
				    "code": "DLTFAILS",
				    "message": "Task doesn't exist"
				}
				""";
		ResponseEntity<String> response = template.exchange(url,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(expectedJson,response.getBody(), true);
	}

	@Test
	public void testretrieveTasksBasedonUserAndStatus_emptylist() throws JSONException
	{
		String url = "http://localhost:9999/api/tasks/user/10/status/Ongoing";
		String expectedJson = """
				{
					"code": "GETALLFAILS",
					"message": "Task list is empty"
				}
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
 
	
	@Test
	@Transactional
    void testUpdateTask() throws JSONException{
        when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));
        boolean result = taskService.updateTask(1, updatedTask);
 
        assertTrue(result);
        assertEquals(updatedTask.getTaskName(), existingTask.getTaskName());
        assertEquals(updatedTask.getDescription(), existingTask.getDescription());
        assertEquals(updatedTask.getDueDate(), existingTask.getDueDate());
        assertEquals(updatedTask.getPriority(), existingTask.getPriority());
        assertEquals(updatedTask.getStatus(), existingTask.getStatus());
        assertEquals(updatedTask.getUser().getUserID(), existingTask.getUser().getUserID());
        assertEquals(updatedTask.getProject().getProjectID(), existingTask.getProject().getProjectID());
        
    }
}