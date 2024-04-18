package com.taskmanagement.test.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
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

import com.taskmanagement.bean.Attachment;
import com.taskmanagement.repositories.AttachmentRepository;
import com.taskmanagement.servicesimpl.AttachmentServiceimpl;

import jakarta.transaction.Transactional;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AttachmentTest {
	@Mock
	private AttachmentRepository attachmentRepository;

	@InjectMocks
	private AttachmentServiceimpl attachmentService;

	private Attachment existingAttachment;

	@BeforeEach
	void setUp() {
		// Create a sample attachment
		existingAttachment = new Attachment();
		existingAttachment.setAttachmentID(1);
		existingAttachment.setFileName("File1.txt");
		existingAttachment.setFilePath("/path/to/file1");
	}
	
	@Autowired
	private TestRestTemplate template;
	@Test
	public void testRetrieveAllAttachments() throws JSONException{
		String endpoint="http://localhost:9999/api/attachments/all";
		String attachments="""
				[
    {
        "attachmentID": 1,
        "fileName": "File1.txt",
        "filePath": "/path/to/file1",
        "task": {
            "taskID": 1,
            "taskName": "Task One",
            "description": "Description for Task One",
            "dueDate": "2022-01-10",
            "priority": "High",
            "status": "In Progress",
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
                "userID": 1,
                "username": "john_doe",
                "password": "password123",
                "email": "john.doe@email.com",
                "fullName": "John Doe"
            }
        }
    },
    {
        "attachmentID": 2,
        "fileName": "File2.txt",
        "filePath": "/path/to/file2",
        "task": {
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
        }
    },
    {
        "attachmentID": 3,
        "fileName": "File3.txt",
        "filePath": "/path/to/file3",
        "task": {
            "taskID": 3,
            "taskName": "Task Three",
            "description": "Description for Task Three",
            "dueDate": "2022-03-20",
            "priority": "Low",
            "status": "Completed",
            "project": {
                "projectID": 2,
                "projectName": "Project Two",
                "description": "Description for Project Two",
                "startDate": "2022-02-01",
                "endDate": "2022-03-01",
                "user": {
                    "userID": 2,
                    "username": "jane_smith",
                    "password": "pass456",
                    "email": "jane.smith@email.com",
                    "fullName": "Jane Smith"
                }
            },
            "user": {
                "userID": 1,
                "username": "john_doe",
                "password": "password123",
                "email": "john.doe@email.com",
                "fullName": "John Doe"
            }
        }
    },
    {
        "attachmentID": 4,
        "fileName": "FeatureList.doc",
        "filePath": "/path/to/FeatureList.doc",
        "task": {
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
        }
    },
    {
        "attachmentID": 5,
        "fileName": "DesignMockup.png",
        "filePath": "/path/to/DesignMockup.png",
        "task": {
            "taskID": 5,
            "taskName": "Design Landing Page",
            "description": "Create a new design for the landing page",
            "dueDate": "2022-03-15",
            "priority": "Medium",
            "status": "In Progress",
            "project": {
                "projectID": 5,
                "projectName": "Website Redesign",
                "description": "Redesigning company website",
                "startDate": "2022-03-10",
                "endDate": "2022-05-31",
                "user": {
                    "userID": 5,
                    "username": "michael_wilson",
                    "password": "secure_password",
                    "email": "michael.wilson@email.com",
                    "fullName": "Michael Wilson"
                }
            },
            "user": {
                "userID": 5,
                "username": "michael_wilson",
                "password": "secure_password",
                "email": "michael.wilson@email.com",
                "fullName": "Michael Wilson"
            }
        }
    },
    {
        "attachmentID": 6,
        "fileName": "CampaignDetails.pdf",
        "filePath": "/path/to/CampaignDetails.pdf",
        "task": {
            "taskID": 6,
            "taskName": "Marketing Campaign",
            "description": "Launching a new marketing campaign",
            "dueDate": "2022-04-01",
            "priority": "High",
            "status": "In Progress",
            "project": {
                "projectID": 6,
                "projectName": "Marketing Campaign",
                "description": "Launching a new marketing campaign",
                "startDate": "2022-04-01",
                "endDate": "2022-05-15",
                "user": {
                    "userID": 6,
                    "username": "sarah_miller",
                    "password": "sarahpass",
                    "email": "sarah.miller@email.com",
                    "fullName": "Sarah Miller"
                }
            },
            "user": {
                "userID": 6,
                "username": "sarah_miller",
                "password": "sarahpass",
                "email": "sarah.miller@email.com",
                "fullName": "Sarah Miller"
            }
        }
    },
    {
        "attachmentID": 7,
        "fileName": "AppDesignConcepts.png",
        "filePath": "/path/to/AppDesignConcepts.png",
        "task": {
            "taskID": 7,
            "taskName": "Mobile App Development",
            "description": "Building a mobile app for iOS and Android",
            "dueDate": "2022-03-01",
            "priority": "High",
            "status": "In Progress",
            "project": {
                "projectID": 7,
                "projectName": "Mobile App Development",
                "description": "Building a mobile app for iOS and Android",
                "startDate": "2022-03-01",
                "endDate": "2022-06-30",
                "user": {
                    "userID": 7,
                    "username": "robert_clark",
                    "password": "robert123",
                    "email": "robert.clark@email.com",
                    "fullName": "Robert Clark"
                }
            },
            "user": {
                "userID": 7,
                "username": "robert_clark",
                "password": "robert123",
                "email": "robert.clark@email.com",
                "fullName": "Robert Clark"
            }
        }
    },
    {
        "attachmentID": 8,
        "fileName": "SupportSystemDocs.doc",
        "filePath": "/path/to/SupportSystemDocs.doc",
        "task": {
            "taskID": 8,
            "taskName": "Customer Support Portal",
            "description": "Developing a customer support portal",
            "dueDate": "2022-05-15",
            "priority": "Medium",
            "status": "In Progress",
            "project": {
                "projectID": 8,
                "projectName": "Customer Support Portal",
                "description": "Developing a customer support portal",
                "startDate": "2022-05-15",
                "endDate": "2022-08-31",
                "user": {
                    "userID": 8,
                    "username": "linda_turner",
                    "password": "lindapass",
                    "email": "linda.turner@email.com",
                    "fullName": "Linda Turner"
                }
            },
            "user": {
                "userID": 8,
                "username": "linda_turner",
                "password": "lindapass",
                "email": "linda.turner@email.com",
                "fullName": "Linda Turner"
            }
        }
    },
    {
        "attachmentID": 9,
        "fileName": "SalesTrainingFeedback.docx",
        "filePath": "/path/to/SalesTrainingFeedback.docx",
        "task": {
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
        }
    },
    {
        "attachmentID": 10,
        "fileName": "KnowledgeBaseUpdates.xlsx",
        "filePath": "/path/to/KnowledgeBaseUpdates.xlsx",
        "task": {
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
    }
]

				""";

	ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(attachments,response.getBody(), true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
	@Test
	public void testRetrieveAttachmentById() throws JSONException{
		String endpoint="http://localhost:9999/api/attachments/2";
		String attachment="""
				{
    "attachmentID": 2,
    "fileName": "File2.txt",
    "filePath": "/path/to/file2",
    "task": {
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
    }
}
				""";
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(attachment,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));

 
}
	@Test
	@Transactional
	void testupdateAttachment() throws JSONException {
		// Arrange
		int attachmentId = 1;
		Attachment updatedAttachment = new Attachment();
		updatedAttachment.setFileName("Untitled.txt");
		updatedAttachment.setFilePath("C:\\Users\\VGINKALA\\Desktop\\Untitled.txt");
		Optional<Attachment> optionalAttachment = Optional.of(existingAttachment);
		when(attachmentRepository.findById(attachmentId)).thenReturn(optionalAttachment);

		// Act
		boolean result = attachmentService.updateAttachment(attachmentId, updatedAttachment);

		// Assert
		assertTrue(result);
		assertEquals("Untitled.txt", existingAttachment.getFileName());
		assertEquals("C:\\Users\\VGINKALA\\Desktop\\Untitled.txt", existingAttachment.getFilePath());
		verify(attachmentRepository).findById(attachmentId);
		verify(attachmentRepository).save(existingAttachment);
	}

	@Test
	public void testDeleteByattachmentId() throws JSONException
	{
		String url = "http://localhost:9999/api/attachments/delete/0";
		String deleteattachment = """
				{
				"code": "DLTFAILS",
				"message": "Attachment Does not exist"
				}
				""";
		ResponseEntity<String> response = template.exchange(url,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(deleteattachment,response.getBody(), true);
	}
	
	@Test
	public void testretrieveAttachmentById_failure() throws JSONException{
		String endpoint="http://localhost:9999/api/attachments/26";
		String attachment="""
				{
					"code": "GETFAILS",
					"message": "Attachment doesn't exist"
				}
				""";
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(attachment,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
}

