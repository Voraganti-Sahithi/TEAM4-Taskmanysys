package com.taskmanagement.test.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.taskmanagement.bean.UserRole;
import com.taskmanagement.repositories.UserRoleRepository;
import com.taskmanagement.servicesimpl.UserRoleServiceImpl;

import jakarta.transaction.Transactional;
 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRoleTest {
	
	@Mock
    private UserRoleRepository userRoleRepositoryMock;
 
    @InjectMocks
    private UserRoleServiceImpl userRoleService;
    
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testGetAllUserRoles() throws JSONException {
	String endpoint="http://localhost:9999/api/userrole/all";
	String userrole = """
			[
    {
        "userRoleID": 1,
        "roleName": "Admin"
    },
    {
        "userRoleID": 2,
        "roleName": "User"
    },
    {
        "userRoleID": 3,
        "roleName": "Manager"
    },
    {
        "userRoleID": 4,
        "roleName": "Product Manager"
    },
    {
        "userRoleID": 5,
        "roleName": "Designer"
    },
    {
        "userRoleID": 6,
        "roleName": "Marketing Specialist"
    },
    {
        "userRoleID": 7,
        "roleName": "Developer"
    },
    {
        "userRoleID": 8,
        "roleName": "Customer Support Representative"
    },
    {
        "userRoleID": 9,
        "roleName": "Sales Trainer"
    },
    {
        "userRoleID": 10,
        "roleName": "Documentation Specialist"
    }
]
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(userrole,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
 
 
	}
	@Test
	public void testGetUserRoleById() throws JSONException {
	String endpoint="http://localhost:9999/api/userrole/6";
	String userrole = """
			{
    "userRoleID": 6,
    "roleName": "Marketing Specialist"
}

			""";
	int userroleId=-1;
	try {
		userroleId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertTrue(userroleId >= 0, "Expected categoryId to be a positive integer");
 
	}
	@Test
	@Transactional
    public void testUpdateUserRoleById()throws JSONException {
        // Arrange
        int userId = 3;
        UserRole updatedUserRole = new UserRole(3, "Manager");
        UserRole existingUserRole = new UserRole(3, "Security");
        when(userRoleRepositoryMock.findById(3)).thenReturn(Optional.of(existingUserRole));
 
        // Act
        boolean result = userRoleService.updateUserRoleById(userId, updatedUserRole);
 
        // Assert
        assertTrue(result, "Expected update to be successful");
        assertEquals(updatedUserRole.getRoleName(), existingUserRole.getRoleName(), "Expected role name to be updated");
        Mockito.verify(userRoleRepositoryMock).save(existingUserRole);
       
    }
	
	@Test
	public void testDeleteUserRoleById() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/userrole/delete/68";
		String userrole = """
				{
    "code": "DLTFAILS",
    "message": "UserRoleid doesn't exist"
}
				""";
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(userrole,response.getBody(), true);
	}
	
	@Test
	public void testgetUserRoleById_failure() throws JSONException {
	String endpoint="http://localhost:9999/api/userrole/15";
	String userrole = """
			{
				"code": "GETALLFAILS",
				"message": "User doesn't exist "
			}
 
			""";
	int userroleId=-1;
	try {
		userroleId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(userroleId >= 0, "Expected categoryId to be a positive integer");
	assertTrue(response.getStatusCode().is4xxClientError());
	
	JSONAssert.assertEquals(userrole,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
 
 
	}
}