package com.taskmanagement.test.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.taskmanagement.bean.Notification;
import com.taskmanagement.repositories.NotificationRepository;
import com.taskmanagement.servicesimpl.NotificationServiceimpl;

import jakarta.transaction.Transactional;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class NotificationTest {
	
	@Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceimpl notificationService;
    
	@Autowired
	private TestRestTemplate template;
	@Test
	public void testRetrieveAllNotifications() throws JSONException{
		String endpoint="http://localhost:9999/api/notifications/all";
		String notifications="""
				[
    {
        "notificationID": 1,
        "text": "Notification for User One",
        "createdAt": "2022-01-15T10:00:00",
        "user": {
            "userID": 1,
            "username": "john_doe",
            "password": "password123",
            "email": "john.doe@email.com",
            "fullName": "John Doe"
        }
    },
    {
        "notificationID": 2,
        "text": "Notification for User Two",
        "createdAt": "2022-02-20T14:45:00",
        "user": {
            "userID": 2,
            "username": "jane_smith",
            "password": "pass456",
            "email": "jane.smith@email.com",
            "fullName": "Jane Smith"
        }
    },
    {
        "notificationID": 3,
        "text": "Notification for User Three",
        "createdAt": "2022-03-30T08:30:00",
        "user": {
            "userID": 3,
            "username": "alex_jones",
            "password": "secret789",
            "email": "alex.jones@email.com",
            "fullName": "Alex Jones"
        }
    },
    {
        "notificationID": 4,
        "text": "Product Launch Meeting Tomorrow",
        "createdAt": "2022-02-14T16:30:00",
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        }
    },
    {
        "notificationID": 5,
        "text": "Reminder: Design Review Meeting",
        "createdAt": "2022-03-12T09:00:00",
        "user": {
            "userID": 5,
            "username": "michael_wilson",
            "password": "secure_password",
            "email": "michael.wilson@email.com",
            "fullName": "Michael Wilson"
        }
    },
    {
        "notificationID": 6,
        "text": "Reminder: Marketing Strategy Meeting Tomorrow",
        "createdAt": "2022-04-04T16:00:00",
        "user": {
            "userID": 6,
            "username": "sarah_miller",
            "password": "sarahpass",
            "email": "sarah.miller@email.com",
            "fullName": "Sarah Miller"
        }
    },
    {
        "notificationID": 7,
        "text": "Deadline Approaching: App UI/UX Design",
        "createdAt": "2022-03-20T12:00:00",
        "user": {
            "userID": 7,
            "username": "robert_clark",
            "password": "robert123",
            "email": "robert.clark@email.com",
            "fullName": "Robert Clark"
        }
    },
    {
        "notificationID": 8,
        "text": "Notification for Support System Implementation",
        "createdAt": "2022-06-10T08:00:00",
        "user": {
            "userID": 8,
            "username": "linda_turner",
            "password": "lindapass",
            "email": "linda.turner@email.com",
            "fullName": "Linda Turner"
        }
    },
    {
        "notificationID": 9,
        "text": "Upcoming Sales Training Session",
        "createdAt": "2022-04-28T10:30:00",
        "user": {
            "userID": 9,
            "username": "peter_anderson",
            "password": "peterpass",
            "email": "peter.anderson@email.com",
            "fullName": "Peter Anderson"
        }
    },
    {
        "notificationID": 10,
        "text": "Reminder: Knowledge Base Review Meeting",
        "createdAt": "2022-07-05T14:00:00",
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
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(notifications,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
	@Test
	public void testRetrieveNotificationById() throws JSONException{
		String endpoint="http://localhost:9999/api/notifications/pujitha";
		String notification="""
				{
    "notificationID": 3
    }
 
				
				""";
		int notificationId=-1;
		try {
			notificationId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(notificationId >= 0, "Expected categoryId to be a positive integer");
}
	
	@Test
	@Transactional
    public void testUpdateNotification() {
        // Mocking
        int notificationId = 1;
        Notification updatedNotification = new Notification();
        updatedNotification.setText("Updated text");
       
        // Assuming necessary setters are called for User

        Notification existingNotification = new Notification();
        existingNotification.setNotificationID(notificationId);

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(existingNotification));

        // Execution
        boolean result = notificationService.updateNotification(notificationId, updatedNotification);

        // Assertion
        assertTrue(result);
        assertEquals(updatedNotification.getText(), existingNotification.getText());
        assertEquals(updatedNotification.getCreatedAt(), existingNotification.getCreatedAt());
        // Add more assertions if needed for User properties
        verify(notificationRepository).save(existingNotification);
    }

	@Test
	public void testDeleteBynotificationId() throws JSONException
	{
		String url = "http://localhost:9999/api/notifications/delete/0";
		String deletenotification = """
				{
				"code": "DLTFAILS",
				"message": "Notification doesn't exists"
				}
				""";
		ResponseEntity<String> response = template.exchange(url,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(deletenotification,response.getBody(), true);
	}
	@Test
	public void testretrieveNotificationById_success() throws JSONException{
		String endpoint="http://localhost:9999/api/notifications/2";
		String notification="""
				{
    "notificationID": 2,
    "text": "Notification for User Two",
    "createdAt": "2022-02-20T14:45:00",
    "user": {
        "userID": 2,
        "username": "jane_smith",
        "password": "pass456",
        "email": "jane.smith@email.com",
        "fullName": "Jane Smith"
    }
}	
				""";
		int notificationId=-1;
		try {
			notificationId = Validation.extractId(endpoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(notification,response.getBody(), false);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
}
