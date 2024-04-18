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
//import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity;
 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRolesTest {
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testGetUserRolesByUserId() throws JSONException {
	String endpoint="/api/userroles/user/hey";
	String projects = """
			[
    {
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        },
        "userRole": {
            "userRoleID": 3,
            "roleName": "Manager"
        }
    }
]
			""";
	int categoryId=-1;
	try {
		categoryId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertFalse(categoryId >= 0, "Expected categoryId to be a positive integer");
}
	
	@Test
	public void testDeleteUserRoles() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/userroles/revoke/15/16";
		String userroles = """
				{
				    "code": "DLTFAILS",
				    "message": "UserRole doesn't exist"
				}
				""";
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(userroles,response.getBody(), true);
	}
	
	@Test
	public void testGetAllUserRoles() throws JSONException {
	String endpoint="/api/userroles/all";
	String getAlluserroles = """
			[
    {
        "user": {
            "userID": 1,
            "username": "john_doe",
            "password": "password123",
            "email": "john.doe@email.com",
            "fullName": "John Doe"
        },
        "userRole": {
            "userRoleID": 2,
            "roleName": "User"
        }
    },
    {
        "user": {
            "userID": 2,
            "username": "jane_smith",
            "password": "pass456",
            "email": "jane.smith@email.com",
            "fullName": "Jane Smith"
        },
        "userRole": {
            "userRoleID": 2,
            "roleName": "User"
        }
    },
    {
        "user": {
            "userID": 3,
            "username": "alex_jones",
            "password": "secret789",
            "email": "alex.jones@email.com",
            "fullName": "Alex Jones"
        },
        "userRole": {
            "userRoleID": 2,
            "roleName": "User"
        }
    },
    {
        "user": {
            "userID": 7,
            "username": "robert_clark",
            "password": "robert123",
            "email": "robert.clark@email.com",
            "fullName": "Robert Clark"
        },
        "userRole": {
            "userRoleID": 2,
            "roleName": "User"
        }
    },
    {
        "user": {
            "userID": 3,
            "username": "alex_jones",
            "password": "secret789",
            "email": "alex.jones@email.com",
            "fullName": "Alex Jones"
        },
        "userRole": {
            "userRoleID": 3,
            "roleName": "Manager"
        }
    },
    {
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        },
        "userRole": {
            "userRoleID": 3,
            "roleName": "Manager"
        }
    },
    {
        "user": {
            "userID": 5,
            "username": "michael_wilson",
            "password": "secure_password",
            "email": "michael.wilson@email.com",
            "fullName": "Michael Wilson"
        },
        "userRole": {
            "userRoleID": 4,
            "roleName": "Product Manager"
        }
    },
    {
        "user": {
            "userID": 5,
            "username": "michael_wilson",
            "password": "secure_password",
            "email": "michael.wilson@email.com",
            "fullName": "Michael Wilson"
        },
        "userRole": {
            "userRoleID": 5,
            "roleName": "Designer"
        }
    },
    {
        "user": {
            "userID": 5,
            "username": "michael_wilson",
            "password": "secure_password",
            "email": "michael.wilson@email.com",
            "fullName": "Michael Wilson"
        },
        "userRole": {
            "userRoleID": 6,
            "roleName": "Marketing Specialist"
        }
    },
    {
        "user": {
            "userID": 8,
            "username": "linda_turner",
            "password": "lindapass",
            "email": "linda.turner@email.com",
            "fullName": "Linda Turner"
        },
        "userRole": {
            "userRoleID": 8,
            "roleName": "Customer Support Representative"
        }
    },
    {
        "user": {
            "userID": 10,
            "username": "natalie_brown",
            "password": "natalie456",
            "email": "natalie.brown@email.com",
            "fullName": "Natalie Brown"
        },
        "userRole": {
            "userRoleID": 10,
            "roleName": "Documentation Specialist"
        }
    }
]
			""";
	int categoryId=-1;
	try {
		categoryId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(getAlluserroles,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
 
}

