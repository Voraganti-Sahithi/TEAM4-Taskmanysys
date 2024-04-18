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
import org.springframework.http.ResponseEntity;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TaskCategoryTest {
	@Autowired
	private TestRestTemplate template;
	@Test
	public void testRetrieveCategoriesByTaskId() throws JSONException
	{
		String endpoint = "/api/taskcategories/task/task1";
		String expectedJson = """
				{
				    "code": "ENDPOINTFAILS",
				    "message": "Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"tyuik\""
				}
				""";
		int taskId=-1;
		try {
			taskId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
        // Check if taskId is an integer value
        assertFalse(taskId >= 0, "Expected taskId to be a positive integer");
	}
	@Test
	public void testRetrieveTasksByCategoryId() throws JSONException
	{
		String endpoint = "/api/taskcategories/category/2";
		String expectedJson = """
				[
				    {
				        "taskID": 1
				    },
				    {
				        "taskID": 2
				    },
				    {
				        "taskID": 3
				    },
				    {
				        "taskID": 7
				    }
				]
							
							""";
		int categoryId=-1;
		try {
			categoryId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
        // Check if taskId is an integer value
        assertTrue(categoryId >= 0, "Expected categoryId to be a positive integer");
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
	
	@Test
	public void testretrieveCategoriesByTaskId_success() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/taskcategories/task/4";
		String expectedJson = """
				[
    {
        "categoryID": 3,
        "categoryName": "Marketing"
    },
    {
        "categoryID": 7,
        "categoryName": "Development"
    }
]
				""";
		int taskId=-1;
		try {
			taskId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
	
	@Test
	public void testretrieveTasksByCategoryId_failure() throws JSONException
	{
		String endpoint = "http://localhost:7105/api/taskcategories/category/16";
		String expectedJson = """
				{
					"code": "GETALLFAILS",
					"message": "No task found for a particular category"
				}
							
							""";
		int categoryId=-1;
		try {
			categoryId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
        
        assertTrue(categoryId >= 0, "Expected categoryId to be a positive integer");
	}

 
}
